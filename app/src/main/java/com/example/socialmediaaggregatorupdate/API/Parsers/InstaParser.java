package com.example.socialmediaaggregatorupdate.API.Parsers;

import android.net.Uri;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.example.socialmediaaggregatorupdate.SM_Data.Platform;
import com.example.socialmediaaggregatorupdate.SM_Data.model.Post;

public class InstaParser implements IParser<JSONArray> {


    public static final String POST_TEXT_KEY = "caption";
    public static final String POST_LINK_KEY = "permalink";
    public static final String POST_TIMESTAMP_KEY = "timestamp";

    private static InstaParser instance;

    private InstaParser() {}

    public static InstaParser getInstance() {
        if (instance == null)
            instance = new InstaParser();
        return instance;
    }

    @Override
    public List<Post> parse(JSONArray data) {
        ArrayList<Post> posts = new ArrayList<>();

        try {
            for (int i = 0; i < data.length(); i++) {
                JSONObject item = data.getJSONObject(i);

                Post.Builder builder = new Post.Builder()
                        .setPlatform(Platform.Instagram)
                        .setBody(item.getString(POST_TEXT_KEY))
                        // Application needs to exit development mode to get user details,
                        // so the username cannot be retrieved during development
                        .setUser("unknown_user")
                        .setRedirectUri(Uri.parse(item.getString(POST_LINK_KEY)))
                        .setPostedDate(DateTime.parse(item.getString(POST_TIMESTAMP_KEY)).toDate());
                posts.add(builder.build());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return posts;
    }
}

