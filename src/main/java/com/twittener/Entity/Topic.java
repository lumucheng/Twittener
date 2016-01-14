package com.twittener.Entity;

public class Topic {
    
    public static final String COL_ID = "id";
    public static final String COL_TOPIC = "topic";
    
    private Integer id;
    private String topic;

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
     * @return the topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * @param topic the topic to set
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }
    
    
    
    
}
