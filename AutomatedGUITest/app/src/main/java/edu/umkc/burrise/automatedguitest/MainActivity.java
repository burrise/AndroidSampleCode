package edu.umkc.burrise.automatedguitest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View incButton = findViewById(R.id.updateButton);
        incButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.updateButton) {
            EditText inputText = (EditText)findViewById(R.id.inputText);
            TextView label = (TextView)findViewById(R.id.textLabel);
            label.setText(inputText.getText().toString());
        }
    }
}