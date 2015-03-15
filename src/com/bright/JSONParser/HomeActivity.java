package com.bright.JSONParser;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import org.json.JSONObject;

public class HomeActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    String URLtoParse = "https://dl.dropboxusercontent.com/u/746330/facts.json";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        URLtoJSONAsyncTask task = new URLtoJSONAsyncTask(URLtoParse, this, new OnURLtoJSONTaskCompleted() {
            @Override
            public void OnTaskCompleted(JSONObject jObject) {
                System.out.print("ALL GOOD");
                Log.d(HomeActivity.this.getClass().getCanonicalName(), "ALL GOOD");
            }
        });

        task.execute();
    }
}
