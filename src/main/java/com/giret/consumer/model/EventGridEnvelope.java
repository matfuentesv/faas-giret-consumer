package com.giret.consumer.model;

public class EventGridEnvelope {
    private String eventType;
    private Object data;

    public String getEventType() { return eventType; }
    public Object getData() { return data; }
}

