package com.example.smetayer.intenttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button send = (Button) findViewById(R.id.send);
        final EditText text = (EditText) findViewById(R.id.input);

        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String user_input = text.getText().toString();
                Log.i("IUT", user_input);
                Intent myIntent = new Intent(v.getContext(),End.class);
                myIntent.putExtra("text",user_input);
                startActivity(myIntent);
            }
        });

    }
}
