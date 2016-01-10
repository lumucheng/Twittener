/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twittener.Manager;

import com.twittener.DAO.TopicAccountsDAO;
import com.twittener.DAO.TopicDAO;
import com.twittener.DAO.TweetDAO;
import com.twittener.DAO.TweetMediaDAO;
import com.twittener.Entity.Topic;
import com.twittener.Entity.TopicAccount;
import com.twittener.Entity.TweetMedia;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import twitter4j.ExtendedMediaEntity;
import twitter4j.MediaEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 *
 * @author Mucheng
 */
public class TwitterManager {

    private final TextToSpeechManager ttsMgr;

    private final TopicDAO topicDAO;
    private final TopicAccountsDAO topicAccountsDAO;
    private final TweetDAO tweetDAO;
    private final TweetMediaDAO tweetMediaDAO;

    public TwitterManager() {

        topicDAO = new TopicDAO();
        topicAccountsDAO = new TopicAccountsDAO();
        tweetDAO = new TweetDAO();
        tweetMediaDAO = new TweetMediaDAO();

        ttsMgr = new TextToSpeechManager();
    }

    public void getAllTweetsByTopic() {

        ArrayList<Topic> topicList = topicDAO.getAllTopics();

        for (Topic topic : topicList) {
            ArrayList<TopicAccount> topicAccountList
                    = topicAccountsDAO.getAccountsByTopic(topic.getId());

            System.out.println("Topic (" + topic.getTopic() + ")");

            for (TopicAccount topicAccount : topicAccountList) {

                System.out.println("Getting tweets from @" + topicAccount.getScreenName());

                searchByUser(topicAccount.getScreenName(),
                        topicAccount.getLatestTweetId());
            }
        }
    }

    private void searchByKeyword(List<String> topics) {
        String queryStr = StringUtils.join(topics, "OR");
        searchTwitterRESTAPI(queryStr, Long.valueOf(0));
    }

    private void searchByUser(String username, Long sinceId) {

        String queryStr = "from:" + username;
        searchTwitterRESTAPI(queryStr, sinceId);
    }

    private void searchTwitterRESTAPI(final String queryStr, final Long sinceId) {

        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query(queryStr);

        // Uncomment if needed
        //query.setSince("2015-01-01");
        query.setSinceId(sinceId);
        query.setLang("en");

        QueryResult result;
        int count = 0;

        NLPManager.init();

        try {
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();

                for (final Status tweet : tweets) {

                    if (tweet.getInReplyToScreenName() == null) {

                        count++;

                        if (NLPManager.findSentiment(tweet.getText()) > 1) {

                            tweetDAO.insertTweet(tweet);

                            MediaEntity[] mediaArray = tweet.getMediaEntities();
                            for (MediaEntity media : mediaArray) {
                                TweetMedia tweetMedia = new TweetMedia();
                                tweetMedia.setMedia_Id(media.getId());
                                tweetMedia.setMedia_Type(media.getType());
                                tweetMedia.setMedia_Url(media.getMediaURL());
                                tweetMedia.setTweet_Id(tweet.getId());
                                tweetMedia.setMedia_Caption("@" + tweet.getUser().getName()
                                        + ":" + tweet.getText());
                                tweetMediaDAO.insertTweetMedia(tweetMedia);
                            }

                            ExtendedMediaEntity[] extMediaArray = tweet.getExtendedMediaEntities();
                            for (ExtendedMediaEntity extended : extMediaArray) {
                                if (extended.getType().equals("video")) {
                                    TweetMedia videoMedia = new TweetMedia();
                                    videoMedia.setMedia_Id(extended.getId());
                                    videoMedia.setMedia_Type(extended.getType());
                                    videoMedia.setMedia_Url(extended.getMediaURL());
                                    videoMedia.setTweet_Id(tweet.getId());
                                    videoMedia.setMedia_Caption("@" + tweet.getUser().getName()
                                            + ":" + tweet.getText());
                                    videoMedia.setMedia_VideoUrl(extended.getVideoVariants()[2].getUrl());
                                    tweetMediaDAO.insertTweetMedia(videoMedia);
                                }
                            }

                            /* Generate wav file on another thread 
                             in order to prevent blocking main thread */
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    final String formattedTweet = tweet.getText().replaceAll("\\S+://\\S+", "");
                                    ttsMgr.convert(formattedTweet, tweet.getId());
                                }

                            }).start();
                        }
                    }
                }
            } while ((query = result.nextQuery()) != null);

            System.out.println("Retrieved " + count + " number of tweets for " + queryStr);
            System.out.println("----------------------------------");
        } 
        catch (TwitterException ex) {
            System.out.println(ex.getErrorMessage());
            // Do logger here
        }
    }

    private void searchTwitterStreamingAPI(String queryStr) {
//             // Enter your consumer key and secret below
//            OAuthService service = new ServiceBuilder()
//                    .provider(TwitterApi.class)
//                    .apiKey("fx2KegxufEJsbKIlsEPL4fLmC")
//                    .apiSecret("78Ft8W1VqJ552Al38I7EoLw1aYdRPvJXX8P3rbgkGf8enKlmCi")
//                    .build();
//
//            // Set your access token
//            Token accessToken = new Token("82587075-vze7tObLc8V5DFLvuMrjpEgJX7xhG5gLSUNRkqY4x", 
//                    "XdmgXkvqRBKfdIjdguJ0zhyaxTWImEvGedJQG5TUU9Ehp");
//
//            // Let's generate the request
//            System.out.println("Connecting to Twitter Public Stream");
//            OAuthRequest request = new OAuthRequest(Verb.POST, STREAM_URI);
//            request.addHeader("version", "HTTP/1.1");
//            request.addHeader("host", "stream.twitter.com");
//            request.setConnectionKeepAlive(true);
//            request.addHeader("user-agent", "Twitter Stream Reader");
//            request.addBodyParameter("track", "sg50"); 
//
//// Set keywords you'd like to track here
//            service.signRequest(accessToken, request);
//            Response response = request.send();
//
//            // Create a reader to read Twitter's stream
//            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getStream()));
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//        }
//        catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
    }
}
