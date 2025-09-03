package com.enactor.exercise.service;

// This is a simple Java object (POJO) to hold our data.
// Jackson (the JSON library) will automatically convert this into a JSON string.
public class DateTimeResponse {
    public String formattedDateTime;
    public long timestamp;

    // A default constructor is needed for JSON serialization
    public DateTimeResponse() {}

    public DateTimeResponse(String formattedDateTime, long timestamp) {
        this.formattedDateTime = formattedDateTime;
        this.timestamp = timestamp;
    }
}