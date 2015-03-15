package com.bright.JSONParser;

import android.graphics.Bitmap;

/**
 * Created by Josh on 3/15/2015.
 */
public class FeedListItem {

    private FeedListAdapter adapter;
    private String titleString;
    private String description;
    private String imageURL;
    private Bitmap bitmap;
    private boolean retrievingBitmap = false;

    public void setAdapter(FeedListAdapter adapter) {
        this.adapter = adapter;
    }

    public String getTitleString() { return titleString; }

    public String getDescription() { return description; }

    public String getImageURL() { return imageURL; }

    public Bitmap getBitmap() { return bitmap; }

    public boolean isRetrievingBitmap() { return retrievingBitmap; }

    public void setTitleString(String titleString) { this.titleString = titleString; }

    public void setDescription(String description) { this.description = description; }

    public void setImageURL (String imageURL) { this.imageURL = imageURL; }

    public void setBitmap (Bitmap bitmap) { this.bitmap = bitmap; }

    public void setIsRetrievingBitmap(boolean retrievingBitmap) { this.retrievingBitmap = retrievingBitmap; }

}
