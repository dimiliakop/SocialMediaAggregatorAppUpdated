package gr.uom.socialmediaaggregator.UI.Main.UI.view_posts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import gr.uom.socialmediaaggregator.R;
import gr.uom.socialmediaaggregator.API.Tasks.GetPostsWithTrend;
import gr.uom.socialmediaaggregator.SMA_Data.DataModel.Post;
import gr.uom.socialmediaaggregator.UI.Main.UI.home.TrendsAdapter;
import twitter4j.Trend;

public class ViewPostsFragment extends Fragment {

    private ListView postList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_posts, container, false);
        this.postList = view.findViewById(R.id.postList);

        List<Post> posts = new ArrayList<>();
        PostsAdapter adapter = new PostsAdapter(view.getContext(), R.layout.post_item, posts, this.postList, this);
        this.postList.setAdapter(adapter);

        Trend trend = (Trend) getArguments().get(TrendsAdapter.TREND_KEY);

        new GetPostsWithTrend(trend, adapter).execute();

        return view;
    }
}