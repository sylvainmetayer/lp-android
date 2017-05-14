package com.example.smetayer.intentimplicite;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final int MY_PERMISSION_TO_CALL = 1;
    public static final int MY_PERMISSION_TO_SMS = 2;

    private String phone;
    private String message;
    private String url;
    private String geo;
    private String maps_query;
    private String extraData;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button call = (Button) findViewById(R.id.call);
        final Button sms = (Button) findViewById(R.id.sms);
        final Button maps = (Button) findViewById(R.id.maps);
        final Button web = (Button) findViewById(R.id.web);

        final EditText data = (EditText) findViewById(R.id.data);
        final EditText extraData = (EditText) findViewById(R.id.extraData);

        //this.phone = "0672065845";
        //this.phone = "0643738482";
        //this.message = "Un SMS envoyé automatiquement ! Qui l'eu cru :D ?";
        //this.url = "https://sylvainmetayer.fr/";
        //this.geo = "0,0";
        //this.maps_query = "1600+Amphitheatre+Parkway,+Mountain+View,+CA+94043";

        this.data = "";
        this.extraData = "";

        extraData.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                MainActivity.this.extraData = extraData.getText().toString();
            }
        });

        data.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                MainActivity.this.data = data.getText().toString();
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSION_TO_CALL);
            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSION_TO_SMS);
            }
        });

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maps(MainActivity.this.data, MainActivity.this.extraData);
            }
        });

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                internet(MainActivity.this.data);
            }
        });
    }

    public void sms(String phone, String message) throws SecurityException {
        // TODO Why is ACTION_VIEW Intent not showing right after click, but after some times ?

        Intent sms = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", phone, null));
        sms.putExtra("sms_body", message);
        Log.i("IUT", "Je démarre l'intent SMS");
        if (sms.resolveActivity(getPackageManager()) != null) {
            startActivity(sms);
        }
    }

    public void call(String phone) throws SecurityException {
        Intent call = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        Log.i("IUT", "Je démarre l'intent call");
        if (call.resolveActivity(getPackageManager()) != null) {
            startActivity(call);
        }
    }

    public void maps(String geo, String query) {
        Uri gmmIntentUri = Uri.parse("geo:" + geo + "?q=" + query);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Log.e("IUT", "Google Maps is not installed on this device!");
        }
    }

    public void internet(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) throws SecurityException {
        switch (requestCode) {
            case MainActivity.MY_PERMISSION_TO_CALL: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("CALL", "User granted permission");
                    call(this.data);
                } else {
                    Log.wtf("CALL", "User denied permissions");
                }
            }
            case MainActivity.MY_PERMISSION_TO_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("SMS", "User granted permission");
                    sms(this.data, this.extraData);
                } else {
                    Log.wtf("SMS", "User denied permissions");
                }
            }
            default: {
                Log.wtf("", "Unknown code : " + requestCode);
            }
        }

    }
}
