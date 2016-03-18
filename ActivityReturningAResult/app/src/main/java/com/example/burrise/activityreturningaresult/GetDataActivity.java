package com.example.burrise.activityreturningaresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class GetDataActivity extends AppCompatActivity  implements View.OnClickListener {

    public final static String START_COUNT_PARM = "startcount";
    public final static String OBJECT_PARM = "objectparm";

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // specifies whether or not the Home button is shown
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // specifies whether or not the Home button has the arrow used for Up Navigation next to it
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View incrementButton = findViewById(R.id.inc_button);
        // This class implements the onClickListener interface.
        // Passing 'this' to setOnClickListener means the
        //   onClick method in this class will get called
        //   when the button is clicked.
        incrementButton.setOnClickListener(this);

        View closeButton = findViewById(R.id.close_button);
        closeButton.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // count = extras.getInt(START_COUNT_PARM);
            MyData md = (MyData)extras.getSerializable(OBJECT_PARM);
            count = md.count;
        }
        updateCount();
    }

    // Up button event handler
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                // navigateUpFromSameTask would take you to parent
                //   activity defined in manifests.
//                NavUtils.navigateUpFromSameTask(this);
                // Up is the same as back in this app. This is non-standard
                returnValueFromActivity();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateCount() {
        TextView t = (TextView)findViewById(R.id.count_value);
        t.setText(Integer.toString(count));
    }

    // Back button
    @Override
    public void onBackPressed() {
        returnValueFromActivity();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.inc_button:
                count++;
                break;
            case R.id.close_button:
                returnValueFromActivity();
                break;
        }
        updateCount();
    }

    private void returnValueFromActivity() {
        Intent intent = new Intent();
        intent.putExtra("keyName", count);
        setResult(RESULT_OK, intent);
        finish();
    }
}
