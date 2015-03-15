package com.bright.JSONParser;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Josh on 3/15/2015.
 */
public class JSONFeedListAdapter extends BaseAdapter {

    private final View rootView;
    private ArrayList<JSONFeedListItem> items;
    private final LayoutInflater inflater;

    public JSONFeedListAdapter(Activity activity, View rootView) {
        super();

        if (activity == null) {
            throw new RuntimeException("No Activity");
        }

        this.rootView = rootView;

        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        items = new ArrayList<JSONFeedListItem>();

    }

    @Override
    public int getCount() { return this.items.size(); }

    @Override
    public Object getItem(int i) {
        JSONFeedListItem item = this.items.get(i);
        item.setAdapter(this);
        return item;
    }

    @Override
    public long getItemId(int i) { return i; }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        JSONFeedListItem item = (JSONFeedListItem) getItem(i);

        view = inflater.inflate(R.layout.json_feed_list_item, null);

        TextView titleView = (TextView)view.findViewById(R.id.list_item_title);
        TextView descView = (TextView)view.findViewById(R.id.list_item_details);

        titleView.setText(item.getTitleString());
        descView.setText(item.getDescription());

        ImageView imageView = (ImageView)view.findViewById(R.id.list_item_image);

        return view;
    }

    public void setItems (ArrayList<JSONFeedListItem> items) { this.items = items; }

}
