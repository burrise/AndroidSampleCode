package edu.umkc.burrise.tddexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/*
  * This example demonstrates three types of automated
  * unit testing:
  * (1) Local tests - JUnit tests that test a simple
  *     java class with no dependencies
  * (2) Local tests with mock objects - testing a java
  *     class that has dependencies on other android
  *     classes. These dependent classes will be
  *     mocked using Mockito.
  * (3) Instrumented tests - Unit tests that run on an
  *     Android device or emulator. The class under test
  *     most likely has a dependency that is hard to mock.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View aboutButton = findViewById(R.id.button);
        aboutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        AppInfo appInfo = new AppInfo(getApplicationContext());

        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(getApplicationContext(),
                appInfo.getAppName(), duration);
        toast.show();
    }

    // If you are following principles of good design
    //   you probably don't have (non-ui) logic in your
    //   activity. The method below is included here
    //   to demonstrate how to test logic in an Activity.
    public int addTwoNumbers(int x, int y) {
        return x + y;
    }
}
