package com.example.smetayer.intenttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class End extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        final TextView text = (TextView) findViewById(R.id.end);

        if (getIntent().getStringExtra("text") != null)
            text.setText(getIntent().getStringExtra("text"));
    }
}
