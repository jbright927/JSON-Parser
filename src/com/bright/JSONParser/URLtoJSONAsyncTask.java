package com.bright.JSONParser;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Josh on 3/15/2015.
 */
public class URLtoJSONAsyncTask extends AsyncTask<Void, Void, Void> {

    private ProgressDialog progressDialog;
    String URLtoParse;
    InputStream inputStream = null;
    String result = "";
    OnTaskCompletedListener listener;

    public URLtoJSONAsyncTask(String URLtoParse, Context ctx, OnTaskCompletedListener listener) {
        super();
        this.URLtoParse = URLtoParse;
        this.listener = listener;
        progressDialog = new ProgressDialog(ctx);
    }

    protected void onPreExecute() {
        progressDialog.setMessage("Retrieving JSON from URL...");
        progressDialog.show();
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface arg0) {
                URLtoJSONAsyncTask.this.cancel(true);
            }
        });
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            URL url = new URL(URLtoParse);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            inputStream = urlConnection.getInputStream();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Convert response to string using String Builder
        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream, "WINDOWS-1252"), 8);
            StringBuilder sBuilder = new StringBuilder();

            String line = null;
            while ((line = bReader.readLine()) != null) {
                sBuilder.append(line + "\n");
            }

            inputStream.close();
            result = sBuilder.toString();

        } catch (Exception e) {
            Log.e("StringBuilding & BufferedReader", "Error converting result " + e.toString());
        }

        return null;

    }


    protected void onPostExecute(Void v) {
        //parse JSON data
        try {
            JSONObject jObject = new JSONObject(result);

            listener.OnTaskCompleted(jObject);
            this.progressDialog.dismiss();

        } catch (JSONException e) {
            Log.e("JSONException", "Error: " + e.toString());
        }
    }

}

