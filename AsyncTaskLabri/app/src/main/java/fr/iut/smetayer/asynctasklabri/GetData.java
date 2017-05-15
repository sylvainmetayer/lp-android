package fr.iut.smetayer.asynctasklabri;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class GetData extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... uri) {
        String key = "114a1c5cd28746fb970125132171505";
        String options = "?key=" + key + ";&format=JSON&cc=no&q=" + uri[1];

        URL url;
        StringBuilder response = new StringBuilder();
        try {
            url = new URL(uri[0] + options);
            Log.i("URL", url.toString());
            HttpURLConnection httpconn = (HttpURLConnection) url.openConnection();
            if (httpconn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader input = new BufferedReader(new InputStreamReader(httpconn.getInputStream()), 8192);
                String strLine;
                while ((strLine = input.readLine()) != null) {
                    response.append(strLine);
                }
                input.close();
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(response.toString());
            return decodeJSON(response.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String decodeJSON(String s) throws JSONException {

        Log.i("RESPONSE", s);

        JSONObject jsonObj = new JSONObject(s);
        final JSONArray data = jsonObj.getJSONArray("data");
        final int n = data.length();
        for (int i = 0; i < n; ++i) {
            final JSONObject dataObject = data.getJSONObject(i);
            System.out.println(dataObject.getInt("id"));
            System.out.println(dataObject.getString("name"));
            System.out.println(dataObject.getString("gender"));
            System.out.println(dataObject.getDouble("latitude"));
            System.out.println(dataObject.getDouble("longitude"));
        }

        String weatherDesc = jsonObj.getJSONObject("data").getJSONArray("current_condition").toString();

        Log.i("IUT", weatherDesc);
        return jsonObj.getJSONObject("data").getJSONObject("current_condition").getJSONObject("weatherDesc").getString("value");
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
//Do anything with response..//
}