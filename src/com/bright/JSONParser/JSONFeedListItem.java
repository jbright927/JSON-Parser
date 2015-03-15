package com.bright.JSONParser;

/**
 * Created by Josh on 3/15/2015.
 */
public class JSONFeedListItem {

    private JSONFeedListAdapter adapter;
    private String titleString;
    private String description;
    private String imageURL;

    public void setAdapter(JSONFeedListAdapter adapter) {
        this.adapter = adapter;
    }

    public String getTitleString() { return titleString; }

    public String getDescription() { return description; }

    public String getImageURL() { return imageURL; }

    public void setTitleString(String titleString) { this.titleString = titleString; }

    public void setDescription(String description) { this.description = description; }

    public void setImageURL (String imageURL) { this.imageURL = imageURL; }

}
