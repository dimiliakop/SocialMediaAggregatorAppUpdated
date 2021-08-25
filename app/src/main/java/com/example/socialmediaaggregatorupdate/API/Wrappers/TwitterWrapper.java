package com.example.socialmediaaggregatorupdate.API.Wrappers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.socialmediaaggregatorupdate.BuildConfig;
import com.example.socialmediaaggregatorupdate.API.Parsers.TwitterParser;
import com.example.socialmediaaggregatorupdate.SM_Data.model.Post;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.StatusUpdate;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterWrapper {

    private static TwitterWrapper instance;

    private Twitter twitter;

    private TwitterWrapper() {}

    public static TwitterWrapper init(String accessToken, String accessTokenSecret, boolean useOAuth2) {
        instance = new TwitterWrapper();
        ConfigurationBuilder config = new ConfigurationBuilder()
                .setApplicationOnlyAuthEnabled(useOAuth2)
                .setOAuthConsumerKey(BuildConfig.TWITTER_API_KEY)
                .setOAuthConsumerSecret(BuildConfig.TWITTER_API_KEY_SECRET)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        instance.twitter = new TwitterFactory(config.build()).getInstance();
        return instance;
    }

    public static TwitterWrapper init() {
        instance = new TwitterWrapper();
        ConfigurationBuilder config = new ConfigurationBuilder()
                .setApplicationOnlyAuthEnabled(true)
                .setOAuthConsumerKey(BuildConfig.TWITTER_API_KEY)
                .setOAuthConsumerSecret(BuildConfig.TWITTER_API_KEY_SECRET);
        instance.twitter = new TwitterFactory(config.build()).getInstance();
        return instance;
    }

    public static TwitterWrapper getInstance() {
        return instance;
    }

    // Get the Twitter trending topics for a specific place in the world
    public List<Trend> fetchTrendsForPlace(int woeid) throws TwitterException {
        Trends trends = twitter.getPlaceTrends(woeid);
        return new ArrayList<>(Arrays.asList(trends.getTrends()));
    }

    // Get the Tweets for a trending topic
    public List<Post> fetchPostsWithTrend(Trend trend) throws TwitterException {
        QueryResult result = twitter.search(new Query(trend.getQuery()));
        return new ArrayList<>(TwitterParser.getInstance().parse(result.getTweets()));
    }

