package gr.uom.socialmediaaggregator.API.Tasks;

import android.os.AsyncTask;

import org.json.JSONException;

import java.util.function.Consumer;

import gr.uom.socialmediaaggregator.API.Wrappers.FacebookWrapper;

public class GetFacebookData extends AsyncTask<Void, Void, Void> {

    private final Consumer<Void> callback;

    public GetFacebookData(Consumer<Void> callback) {
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            FacebookWrapper wrapper = FacebookWrapper.getInstance();
            if (wrapper != null)
                wrapper.getUserCredentials();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void v) {
        callback.accept(v);
    }
}
