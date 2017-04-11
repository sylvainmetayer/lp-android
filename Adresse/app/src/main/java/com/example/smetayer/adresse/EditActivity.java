package com.example.smetayer.adresse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        final EditText address = (EditText) findViewById(R.id.address_edit);
        final EditText name = (EditText) findViewById(R.id.name_edit);
        final EditText phone = (EditText) findViewById(R.id.phone_edit);

        final Button submit = (Button) findViewById(R.id.submit);
        final Button cancel = (Button) findViewById(R.id.cancel);

        if (getIntent().getStringExtra("address") != null)
            address.setText(getIntent().getStringExtra("address"));

        if (getIntent().getStringExtra("phone") != null)
            phone.setText(getIntent().getStringExtra("phone"));

        if (getIntent().getStringExtra("name") != null)
            name.setText(getIntent().getStringExtra("name"));


        final String original_address, original_name, original_phone;

        original_address = address.getText().toString();
        original_name = name.getText().toString();
        original_phone = phone.getText().toString();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s_address, s_phone, s_name;
                s_address = address.getText().toString();
                s_phone = phone.getText().toString();
                s_name = name.getText().toString();
                Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                myIntent.putExtra("address", s_address);
                myIntent.putExtra("phone", s_phone);
                myIntent.putExtra("name", s_name);
                startActivity(myIntent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                myIntent.putExtra("address", original_address);
                myIntent.putExtra("phone", original_phone);
                myIntent.putExtra("name", original_name);
                startActivity(myIntent);
            }
        });

    }
}
