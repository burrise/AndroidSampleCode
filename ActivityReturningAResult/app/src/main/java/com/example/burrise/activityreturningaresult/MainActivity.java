package com.example.burrise.activityreturningaresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/*
  This program shows an example of passing data to and
    receiving data from an Activity.
  It also shows how to implement the up option on the
    action bar.
  For more information about the the back stack and difference
    between back and up, see:
    http://www.peachpit.com/articles/article.aspx?p=1874864
    http://developer.android.com/design/patterns/navigation.html
    http://developer.android.com/training/design-navigation/index.html
    http://developer.android.com/guide/components/tasks-and-back-stack.html

 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View decrementButton = findViewById(R.id.start_activity_button);
        decrementButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, GetDataActivity.class);
        // Example of passing data to an Activity
        // You can pass primitive types like:
        i.putExtra (GetDataActivity.START_COUNT_PARM, 3);

        // You can pass objects as serializable.
        // Note, you can pass objects via serializable or parcelable.
        //   serializable is easier (less code) but parcelable is faster.
        //   Ref: http://www.developerphil.com/parcelable-vs-serializable/
        MyData md = new MyData();
        md.count = 4;
        i.putExtra (GetDataActivity.OBJECT_PARM, md);
        // TBD: make 1 a named constant
        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                int result=data.getIntExtra("keyName",-1);
                TextView t = (TextView)findViewById(R.id.current_value);
                t.setText(Integer.toString(result));
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult
}
