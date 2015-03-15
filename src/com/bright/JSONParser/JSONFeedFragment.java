package com.bright.JSONParser;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        buildJSONFeed();

        return rootView;

    }

    void buildJSONFeed() {

        URLtoJSONAsyncTask task = new URLtoJSONAsyncTask(URLtoParse, getActivity(), new OnURLtoJSONTaskCompleted() {
            @Override
            public void OnTaskCompleted(JSONObject jObject) {
                System.out.print("ALL GOOD");
                Log.d(getClass().getCanonicalName(), "ALL GOOD");
            }
        });

        task.execute();

    }

}
