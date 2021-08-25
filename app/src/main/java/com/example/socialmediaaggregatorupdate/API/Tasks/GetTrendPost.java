package com.example.socialmediaaggregatorupdate.API.Tasks;

import android.os.AsyncTask;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import com.example.socialmediaaggregatorupdate.API.Comparators.PostComparator;
import com.example.socialmediaaggregatorupdate.API.Comparators.PostChronologyComparator;
import com.example.socialmediaaggregatorupdate.API.Parsers.InstaParser;
import com.example.socialmediaaggregatorupdate.API.Wrappers.FacebookWrapper;
import com.example.socialmediaaggregatorupdate.API.Wrappers.TwitterWrapper;
import com.example.socialmediaaggregatorupdate.SM_Data.model.Post;
import com.example.socialmediaaggregatorupdate.ui.main.ui.view_posts.PostsAdapter;
import twitter4j.Trend;

public class GetTrendPost extends AsyncTask<Void, Void, List<Post>> {

    private static final PostComparator COMPARATOR = new PostChronologyComparator();

    private final Trend trend;
    private final PostsAdapter adapter;

    public GetTrendPost(Trend trend, PostsAdapter adapter) {
        this.trend = trend;
        this.adapter = adapter;
    }

    @Override
    protected List<Post> doInBackground(Void... voids) {
        List<Post> posts = new ArrayList<>();

        try {
            // Twitter
            posts.addAll(TwitterWrapper.getInstance().fetchPostsWithTrend(trend));

            // Instagram
            JSONArray data = FacebookWrapper.getInstance().getInstagramPostsWithTrend(trend.getName());
            posts.addAll(InstagramParser.getInstance().parse(data));

            // IMPORTANT: Fetching posts from Facebook is NOT supported. sadge
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return posts;
    }

    @Override
    protected void onPostExecute(List<Post> posts) {
        // Sort the list from newest to oldest and display it
        posts.sort(COMPARATOR);
        adapter.setPostList(posts);
    }

}

