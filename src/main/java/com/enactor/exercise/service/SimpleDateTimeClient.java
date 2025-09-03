package com.enactor.exercise.service;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

public class SimpleDateTimeClient {

    public static void main(String[] args) {
        // Swing UIs should be created on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        // Create the main window (JFrame)
        JFrame frame = new JFrame("Date/Time Service Client");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 150);

        // Create a label to show the result
        JLabel timeLabel = new JLabel("Click the button to fetch the time...");
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create a button to trigger the service call
        JButton fetchButton = new JButton("Get Current Time (JSON)");

        // Define what happens when the button is clicked
        fetchButton.addActionListener(e -> {
            timeLabel.setText("Fetching...");
            // Run the web service call in a separate thread to avoid freezing the UI
            new Thread(() -> {
                try {
                    // Create a Jersey client
                    Client client = ClientBuilder.newClient();
                    // Define the target URL of our service
                    WebTarget target = client.target("http://localhost:8080/EnactorWebService/api/datetime/json");

                    // Make the GET request and expect a JSON response
                    // Ask Jersey to convert the JSON response into our DateTimeResponse object
                    DateTimeResponse response = target.request(MediaType.APPLICATION_JSON).get(DateTimeResponse.class);

                    // Update the UI with the result (must be done back on the Swing thread)
                    SwingUtilities.invokeLater(() -> timeLabel.setText(response.formattedDateTime));

                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> timeLabel.setText("Error: " + ex.getMessage()));
                    ex.printStackTrace();
                }
            }).start();
        });

        // Add components to the frame
        frame.getContentPane().add(timeLabel, "Center");
        frame.getContentPane().add(fetchButton, "South");

        // Make the window visible
        frame.setVisible(true);
    }
}