package com.bright.JSONParser;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * Created by Josh on 3/15/2015.
 */
public class URLtoBitmapAsyncTask extends AsyncTask<Void, Void, Void> {

    String URLtoParse;
    InputStream inputStream = null;
    Bitmap bitmap = null;
    OnTaskCompletedListener listener;

    public URLtoBitmapAsyncTask(String URLtoParse, OnTaskCompletedListener listener) {
        super();
        this.URLtoParse = URLtoParse;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            URL url = new URL(URLtoParse);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(1000);

            inputStream = urlConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);

        } catch (MalformedURLException e) {
            Log.e(getClass().getCanonicalName(), "URL is malformed!");
        } catch (IOException e) {
            Log.e(getClass().getCanonicalName(), "Couldn't retrieve image from URL!");
        }

        return null;

    }

    protected void onPostExecute(Void v) {
        listener.OnTaskCompleted(bitmap);
    }

}

