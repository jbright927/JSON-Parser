package com.bright.JSONParser;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Josh on 3/15/2015.
 */
public class JSONFeedFragment extends Fragment {

    private View rootView;
    private JSONObject jsonFeed;
    private JSONFeedListAdapter adapter;
    String URLtoParse = "https://dl.dropboxusercontent.com/u/746330/facts.json";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_jsonfeed, container, false);

        buildListView();
        buildJSONFeed();

        return rootView;

    }

    private void buildListView() {
        adapter = new JSONFeedListAdapter(getActivity(), rootView);
        ListView listView = (ListView) rootView.findViewById(R.id.fragment_jsonfeed_listview);
        listView.setAdapter(adapter);
    }

    private void buildJSONFeed() {

        URLtoJSONAsyncTask task = new URLtoJSONAsyncTask(URLtoParse, rootView.getContext(), new OnTaskCompleted() {
            @Override
            public void OnTaskCompleted(Object jObject) {
                jsonFeed = (JSONObject)jObject;

                buildTitle();
                buildItemList();
            }
        });

        task.execute();

    }

    private void buildTitle() {
        try {
            getActivity().getActionBar().setTitle(jsonFeed.getString("title"));
        }
        catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void buildItemList() {

        ArrayList<JSONFeedListItem> itemList = new ArrayList<JSONFeedListItem>();

        try {
            for (int i = 0; i < jsonFeed.getJSONArray("rows").length(); i++) {

                JSONFeedListItem item = new JSONFeedListItem();

                JSONObject jsonObject = jsonFeed.getJSONArray("rows").getJSONObject(i);

                item.setTitleString(jsonObject.getString("title"));
                item.setDescription(jsonObject.getString("description"));
                item.setImageURL(jsonObject.getString("imageHref"));

                item.setAdapter(adapter);

                itemList.add(item);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        adapter.setItems(itemList);
        adapter.notifyDataSetChanged();

    }

}
