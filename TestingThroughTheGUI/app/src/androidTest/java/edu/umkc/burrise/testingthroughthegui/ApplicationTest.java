package edu.umkc.burrise.testingthroughthegui;

// References:
//   https://developer.android.com/training/activity-testing/activity-functional-testing.html
//   http://evgenii.com/blog/testing-activity-in-android-studio-tutorial-part-3/

import android.app.Application;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ApplicationTestCase;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ApplicationTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity activity;

    public ApplicationTest() {
        super(MainActivity.class);
    }

    // If you want to send key events via your test, you have to turn off
    // the touch mode in the emulator via setActivityInitialTouchMode(false)
    // in your setup() method of the test.
    public void setUp(  )  {
        // setActivityInitialTouchMode(false) must be called before the
        //   activity is created.
        setActivityInitialTouchMode(false);
        activity = (MainActivity) getActivity();
    }

    public void testLabelUpdate() throws Exception {

        Button updateButton;
        updateButton = (Button) activity.findViewById(R.id.updateButton);

        TextView displayLabel;
        displayLabel = (TextView) activity.findViewById(R.id.textLabel);

        final EditText inputField;
        inputField = (EditText) activity.findViewById(R.id.inputText);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                inputField.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("UMKC4");
        getInstrumentation().waitForIdleSync();

        // TouchUtils handles the sync with the main thread internally
        TouchUtils.clickView(this, updateButton);
        // Note, the following line was missing in an earlier version
        //  of this program. It is definitely needed. If you don't wait
        //  for the clickView() update to finish, the assert below
        //  will fail.
        getInstrumentation().waitForIdleSync();

        // displayed value should e 1 now.
        assertEquals("label not updated properly","UMKC4",displayLabel.getText().toString());
    }
}
