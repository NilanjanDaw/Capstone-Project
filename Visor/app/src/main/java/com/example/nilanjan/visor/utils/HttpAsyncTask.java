package com.example.nilanjan.visor.utils;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by nilan on 01-Aug-16.
 */
public class HttpAsyncTask extends AsyncTask<String, Void, String> {

    private static String TAG = "HttpAsyncTask";
    public JSONArray jsonResponseArray;
    public OnFinish delegate;

    public HttpAsyncTask(OnFinish delegate) {
        this.delegate = delegate;
    }

    @Override
    protected String doInBackground(String... params) {
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;
        String urlString = params[0];

        Log.d(TAG, "doInBackground: " + urlString);
        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            inputStream = new BufferedInputStream(urlConnection.getInputStream());
            urlConnection.setRequestMethod("GET");
            int statusCode = urlConnection.getResponseCode();
            if (statusCode == 200) {
                Log.d(TAG, "doInBackground: Request: Successful");
                String result = readResponse(inputStream);
                delegate.processData(jsonResponseArray);
                //Log.d("Response", result);
            } else {
                Log.d(TAG, "doInBackground: Request: Failed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String readResponse(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line, result = "";
        try {
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            JSONObject jsonResponse = new JSONObject(result);
            jsonResponseArray = jsonResponse.optJSONArray("results");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public interface OnFinish {
        void processData(JSONArray array);
    }
}
