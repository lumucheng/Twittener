package com.twittener.Entity;

public class TweetHyperlink {
    
    public static final String TBL_NAME = "TBL_TWEETS_LINK";
    public static final String COL_ID = "id";
    public static final String COL_HYPERLINK = "hyperlink";
    public static final String COL_TWEET_ID = "tweet_id";
    
    private Integer id;
    private String hyperlink;
    private Long tweet_Id;

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
     * @return the hyperlink
     */
    public String getHyperlink() {
        return hyperlink;
    }

    /**
     * @param hyperlink the hyperlink to set
     */
    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }

    /**
     * @return the tweet_Id
     */
    public Long getTweet_Id() {
        return tweet_Id;
    }

    /**
     * @param tweet_Id the tweet_Id to set
     */
    public void setTweet_Id(Long tweet_Id) {
        this.tweet_Id = tweet_Id;
    }
    
}
