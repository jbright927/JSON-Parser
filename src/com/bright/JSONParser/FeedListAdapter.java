package com.bright.JSONParser;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
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
public class FeedListAdapter extends BaseAdapter {

    private final View rootView;
    private ArrayList<FeedListItem> items;
    private final LayoutInflater inflater;

    public FeedListAdapter(Activity activity, View rootView) {
        super();

        if (activity == null) {
            throw new RuntimeException("No Activity");
        }

        this.rootView = rootView;

        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        items = new ArrayList<FeedListItem>();

    }

    @Override
    public int getCount() { return this.items.size(); }

    @Override
    public Object getItem(int i) {
        FeedListItem item = this.items.get(i);
        item.setAdapter(this);
        return item;
    }

    @Override
    public long getItemId(int i) { return i; }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final FeedListItem item = (FeedListItem) getItem(i);

        view = inflater.inflate(R.layout.json_feed_list_item, null);

        TextView titleView = (TextView) view.findViewById(R.id.list_item_title);
        TextView descView = (TextView) view.findViewById(R.id.list_item_details);

        Context ctx = view.getContext();

        titleView.setText(item.getTitleString());
        Typeface typeFaceA = Typeface.createFromAsset(ctx.getAssets(), "fonts/Cambria Bold.ttf");
        titleView.setTypeface(typeFaceA);

        if (!item.getDescription().equals("null")) {
            descView.setText(item.getDescription());
            Typeface typeFaceB = Typeface.createFromAsset(ctx.getAssets(), "fonts/Arial Regular.ttf");
            descView.setTypeface(typeFaceB);
            descView.setVisibility(View.VISIBLE);
        }

        if (!item.getImageURL().isEmpty()) {

            final ImageView imageView = (ImageView) view.findViewById(R.id.list_item_image);

            if (item.getImageURL().contains("flag"))
                Log.d(getClass().getCanonicalName(), "FLAG A");

            if (item.getBitmap() == null && !item.isRetrievingBitmap()) {
                URLtoBitmapAsyncTask task = new URLtoBitmapAsyncTask(item.getImageURL(), new OnTaskCompletedListener() {
                    @Override
                    public void OnTaskCompleted(Object object) {

                        Bitmap bm;

                        if (object == null)
                            bm = BitmapFactory.decodeResource(imageView.getResources(), R.drawable.ic_action_help);
                        else
                            bm = (Bitmap) object;

                        imageView.setImageBitmap(bm);
                        item.setBitmap(bm);

                        if (item.getImageURL().contains("flag"))
                            Log.d(getClass().getCanonicalName(), "FLAG B");

                        Log.d(getClass().getCanonicalName(), ""+item.getImageURL() + ", " + bm.getByteCount());
                        notifyDataSetChanged();

                        item.setIsRetrievingBitmap(false);
                    }
                });

                item.setIsRetrievingBitmap(true);
                imageView.setVisibility(View.VISIBLE);
                task.execute();
            }
            else if (item.getBitmap() != null) {
                imageView.setImageBitmap(item.getBitmap());
                imageView.setVisibility(View.VISIBLE);
            }
        }

        return view;
    }

    public void setItems (ArrayList<FeedListItem> items) { this.items = items; }

}
