package com.enactor.exercise.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

// This annotation defines the base URL path for this class.
// Full URL will be something like: .../api/datetime
@Path("datetime")
public class DateTimeService {

    /**
     * This method handles HTTP GET requests and returns the current time as a simple string.
     * The @Produces annotation tells Jersey to format the output as plain text.
     */
    @GET
    @Path("string") // This makes the URL for this method .../api/datetime/string
    @Produces(MediaType.TEXT_PLAIN)
    public String getCurrentDateTimeAsString() {
        // Get the current time and format it
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "The current date and time is: " + now.format(formatter);
    }

    /**
     * This method handles HTTP GET requests and returns the current time as a JSON object.
     * The @Produces annotation tells Jersey to format the output as JSON.
     * It returns a simple Java object (DateTimeResponse), which Jersey will automatically convert to JSON.
     */
    @GET
    @Path("json") // This makes the URL for this method .../api/datetime/json
    @Produces(MediaType.APPLICATION_JSON)
    public DateTimeResponse getCurrentDateTimeAsJson() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Create a new response object and return it
        return new DateTimeResponse(now.format(formatter), System.currentTimeMillis());
    }
}