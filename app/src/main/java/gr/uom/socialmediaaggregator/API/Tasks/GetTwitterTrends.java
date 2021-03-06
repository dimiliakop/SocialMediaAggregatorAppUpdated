package gr.uom.socialmediaaggregator.API.Tasks;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import gr.uom.socialmediaaggregator.API.Wrappers.TwitterWrapper;
import twitter4j.Trend;
import twitter4j.TwitterException;

public class GetTwitterTrends extends AsyncTask<Void, Void, List<Trend>> {

    private static final int GREECE_WOEID = 23424833; // In a real environment, get it from user's location

    private final MutableLiveData<List<Trend>> mutableTrends;

    public GetTwitterTrends(MutableLiveData<List<Trend>> mutableTrends) {
        this.mutableTrends = mutableTrends;
    }

    @Override
    protected List<Trend> doInBackground(Void... voids) {
        try {
            return TwitterWrapper.getInstance().fetchTrendsForPlace(GREECE_WOEID);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Trend> trends) {
        mutableTrends.setValue(trends);
    }
}
