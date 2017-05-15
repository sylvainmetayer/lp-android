package fr.iut.smetayer.asynctasklabri;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("IUT", "Bonjour !");
                TextView textView = (TextView) findViewById(R.id.text);
                GetData getData = new GetData();

                String city = "Talence";
                String url = "http://api.worldweatheronline.com/premium/v1/weather.ashx";
                try {
                    String result = getData.execute(url, city).get();
                    textView = (TextView) findViewById(R.id.text);
                    textView.setText("Il fait '" + result + "' à " + city);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }

            }
        });

    }


}
