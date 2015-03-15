package com.bright.JSONParser;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Josh on 3/15/2015.
 */
public class JSONFeedFragment extends Fragment {

    private View rootView;
    String URLtoParse = "https://dl.dropboxusercontent.com/u/746330/facts.json";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_jsonfeed, container, false);

        Log.d("BLARGH", "BLARGH");

        buildJSONFeed();

        return rootView;

    }

    private void buildJSONFeed() {

        URLtoJSONAsyncTask task = new URLtoJSONAsyncTask(URLtoParse, rootView.getContext(), new OnURLtoJSONTaskCompleted() {
            @Override
            public void OnTaskCompleted(JSONObject jObject) {
                Log.d(getClass().getCanonicalName(), "ALL GOOD");

                try {
                    Log.d(getClass().getCanonicalName(), jObject.getString("title"));
                    JSONFeedFragment.this.getActivity().getActionBar().setTitle(jObject.getString("title"));
                }
                catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        task.execute();

    }

}
