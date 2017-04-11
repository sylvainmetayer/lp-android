package com.example.smetayer.calculatrice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText number1 = (EditText) findViewById(R.id.number1);
        final EditText number2 = (EditText) findViewById(R.id.number2);
        result = (EditText) findViewById(R.id.result);

        final Button addition = (Button) findViewById(R.id.addition);
        addition.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int first = Integer.parseInt(number1.getText().toString());
                int second = Integer.parseInt(number2.getText().toString());
                int result_value = first + second;
                result.setText(String.valueOf(result_value));
            }
        });

        final Button soustraction = (Button) findViewById(R.id.soustraction);
        soustraction.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int first = Integer.parseInt(number1.getText().toString());
                int second = Integer.parseInt(number2.getText().toString());
                int result_value = first - second;
                result.setText(String.valueOf(result_value));
            }
        });

        final Button multiplication = (Button) findViewById(R.id.multiplication);
        multiplication.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int first = Integer.parseInt(number1.getText().toString());
                int second = Integer.parseInt(number2.getText().toString());
                int result_value = first * second;
                result.setText(String.valueOf(result_value));
            }
        });

        final Button division = (Button) findViewById(R.id.division);
        division.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int first = Integer.parseInt(number1.getText().toString());
                int second = Integer.parseInt(number2.getText().toString());
                int result_value = first / second;
                result.setText(String.valueOf(result_value));
            }
        });


    }
}
