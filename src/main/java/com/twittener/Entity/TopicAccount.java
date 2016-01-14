package com.twittener.Entity;

public class TopicAccount {
    
    public static final String TBL_NAME = "TBL_TOPIC_ACCOUNTS";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_SCREENNAME = "screenname";
    public static final String COL_TWITTER_ID = "twitter_id";
    public static final String COL_LATEST_TWEET_ID = "latest_tweet_id";
    public static final String COL_TOPIC_ID = "topic_id";
    
    private Integer id;
    private String name;
    private String screenName;
    private Long twitterId;
    private Long latestTweetId;
    private Integer topicId;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the screenName
     */
    public String getScreenName() {
        return screenName;
    }

    /**
     * @param screenName the screenName to set
     */
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    /**
     * @return the twitterId
     */
    public Long getTwitterId() {
        return twitterId;
    }

    /**
     * @param twitterId the twitterId to set
     */
    public void setTwitterId(Long twitterId) {
        this.twitterId = twitterId;
    }

    /**
     * @return the latestTweetId
     */
    public Long getLatestTweetId() {
        return latestTweetId;
    }

    /**
     * @param latestTweetId the latestTweetId to set
     */
    public void setLatestTweetId(Long latestTweetId) {
        this.latestTweetId = latestTweetId;
    }

    /**
     * @return the topicId
     */
    public Integer getTopicId() {
        return topicId;
    }

    /**
     * @param topicId the topicId to set
     */
    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }
}
