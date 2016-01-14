package com.twittener.Entity;

public class TweetMedia {
    
    public static final String TBL_NAME = "TBL_TWEETS_MEDIA";
    public static final String COL_ID = "id";
    public static final String COL_MEDIA_ID = "media_id";
    public static final String COL_MEDIA_URL = "media_url";
    public static final String COL_MEDIA_TYPE = "media_type";
    public static final String COL_TWEET_ID = "tweet_id";
    public static final String COL_MEDIAL_VIDEOURL = "media_video_url";
    
    private Integer id;
    private Long media_Id;
    private String media_Type;
    private String media_Url;
    private String media_Caption;
    private String media_VideoUrl;
    private Long tweet_Id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getMedia_Id() {
        return media_Id;
    }

    public void setMedia_Id(Long media_Id) {
        this.media_Id = media_Id;
    }

    public String getMedia_Type() {
        return media_Type;
    }

    public void setMedia_Type(String media_Type) {
        this.media_Type = media_Type;
    }

    public String getMedia_Url() {
        return media_Url;
    }

    public void setMedia_Url(String media_Url) {
        this.media_Url = media_Url;
    }

    public Long getTweet_Id() {
        return tweet_Id;
    }

    public void setTweet_Id(Long tweet_Id) {
        this.tweet_Id = tweet_Id;
    }

    public String getMedia_Caption() {
        return media_Caption;
    }

    public void setMedia_Caption(String media_Caption) {
        this.media_Caption = media_Caption;
    }

    public String getMedia_VideoUrl() {
        return media_VideoUrl;
    }

    public void setMedia_VideoUrl(String media_VideoUrl) {
        this.media_VideoUrl = media_VideoUrl;
    }
}