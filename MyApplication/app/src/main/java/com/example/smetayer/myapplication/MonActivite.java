package com.example.smetayer.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MonActivite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("IUT", "Je suis dans la méthode onCreate");
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("IUT", "Je suis dans la méthode onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("IUT", "Je suis dans la méthode onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("IUT", "Je suis dans la méthode onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("IUT", "Je suis dans la méthode onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("IUT", "Je suis dans la méthode onPause");
    }
}
