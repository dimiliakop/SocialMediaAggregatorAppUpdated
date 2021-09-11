package gr.uom.socialmediaaggregator.API.Tasks;

import android.os.AsyncTask;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import gr.uom.socialmediaaggregator.API.Comparators.PostComparator;
import gr.uom.socialmediaaggregator.API.Comparators.NewestToOldestPostComparator;
import gr.uom.socialmediaaggregator.API.Parsers.IG_Parser;
import gr.uom.socialmediaaggregator.API.Wrappers.FacebookWrapper;
import gr.uom.socialmediaaggregator.API.Wrappers.TwitterWrapper;
import gr.uom.socialmediaaggregator.SMA_Data.DataModel.Post;
import gr.uom.socialmediaaggregator.UI.Main.UI.view_posts.PostsAdapter;
import twitter4j.Trend;

public class GetPostsWithTrend extends AsyncTask<Void, Void, List<Post>> {

    private static final PostComparator COMPARATOR = new NewestToOldestPostComparator();

    private final Trend trend;
    private final PostsAdapter adapter;

    public GetPostsWithTrend(Trend trend, PostsAdapter adapter) {
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
            posts.addAll(IG_Parser.getInstance().parse(data));

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
