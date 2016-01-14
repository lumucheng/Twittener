package com.twittener.Entity;

public enum TopicEnum {
    TECHNOLOGY(1), NEWS(2), ENTERTAINMENT(3);
    private final int value;
    
    private TopicEnum(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
