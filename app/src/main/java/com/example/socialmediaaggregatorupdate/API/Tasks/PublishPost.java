package com.example.socialmediaaggregatorupdate.API.Tasks;

import android.net.Uri;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.example.socialmediaaggregatorupdate.API.Wrappers.FacebookWrapper;
import com.example.socialmediaaggregatorupdate.API.Wrappers.TwitterWrapper;
import com.example.socialmediaaggregatorupdate.SM_Data.Platform;

public class PublishPost extends AsyncTask<Void, Void, Void> {

    private final String message;
    private final Uri imageUri;
    private final List<Platform> platforms;

    private final List<Consumer<Void>> callbackList = new ArrayList<>();

    public PublishPost(String message, Uri imageUri, List<Platform> platforms) {
        this.message = message;
        this.imageUri = imageUri;
        this.platforms = platforms;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        platforms.forEach(platform -> {
            try {
                // Possible optimization: make each platform publishing async to reduce time
                switch (platform) {
                    case Twitter:
                        TwitterWrapper.getInstance().publishTweet(
                                message,
                                imageUri != null ? imageUri.toString() : null
                        );
                        break;
                    case Facebook:
                        FacebookWrapper.getInstance().publishPostToFacebookPage(
                                message,
                                imageUri != null ? imageUri.toString() : null
                        );
                        break;
                    case Instagram:
                        FacebookWrapper.getInstance().publishPostToInstagram(
                                message,
                                imageUri != null ? imageUri.toString() : null
                        );
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        return null;
    }

    @Override
    protected void onPostExecute(Void v) {
        callbackList.forEach(callback -> {
            callback.accept(v);
        });
    }

    public void addCallback(Consumer<Void> callback) {
        callbackList.add(callback);
    }
}

