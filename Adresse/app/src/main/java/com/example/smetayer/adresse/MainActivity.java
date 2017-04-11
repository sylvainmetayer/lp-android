package com.example.smetayer.adresse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText address = (EditText) findViewById(R.id.address_edit);
        final EditText name = (EditText) findViewById(R.id.name_edit);
        final EditText phone = (EditText) findViewById(R.id.phone_edit);

        if (getIntent().getStringExtra("address") != null)
            address.setText(getIntent().getStringExtra("address"));

        if (getIntent().getStringExtra("phone") != null)
            phone.setText(getIntent().getStringExtra("phone"));

        if (getIntent().getStringExtra("name") != null)
            name.setText(getIntent().getStringExtra("name"));

        final Button edit = (Button) findViewById(R.id.edit);

        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String s_address, s_name, s_phone;
                s_address = address.getText().toString();
                s_phone = phone.getText().toString();
                s_name = name.getText().toString();

                Intent myIntent = new Intent(v.getContext(), EditActivity.class);
                myIntent.putExtra("address", s_address);
                myIntent.putExtra("phone", s_phone);
                myIntent.putExtra("name", s_name);
                startActivity(myIntent);
            }
        });

    }
}
