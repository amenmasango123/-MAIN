/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Message class – Part 2 + Part 3
 * @author Student
 */
public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    // ========== PART 3 – Parallel Arrays ==========
    private static String[] sentMessages = new String[20];
    private static String[] disregardedMessages = new String[20];
    private static String[] storedMessages = new String[20];
    private static String[] messageHashes = new String[20];
    private static String[] messageIDs = new String[20];

    private static int sentCount = 0;
    private static int disregardedCount = 0;
    private static int storedCount = 0;

    // Constructor
    public Message() {
        this.messageID = generateMessageID();
    }

    private String generateMessageID() {
        Random random = new Random();
        long number = 1000000000L + (long) (random.nextDouble() * 9000000000L);
        return String.valueOf(number);
    }

    // ========== Getters & Setters ==========
    public String getMessageID() { return messageID; }
    public void setMessageID(String messageID) { this.messageID = messageID; }

    public int getMessageNumber() { return messageNumber; }
    public void setMessageNumber(int messageNumber) { this.messageNumber = messageNumber; }

    public String getRecipient() { return recipient; }
    public void setRecipient(String recipient) { this.recipient = recipient; }

    public String getMessageText() { return messageText; }
    public void setMessageText(String messageText) { this.messageText = messageText; }

    public String getMessageHash() { return messageHash; }

    // ========== Part 2 Methods ==========

    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        if (recipient == null) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
        if (Pattern.matches("^\\+[0-9]{10,12}$", recipient)) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    public String createMessageHash() {
        if (messageID == null || messageID.length() < 2 || messageText == null || messageText.trim().isEmpty()) {
            this.messageHash = "00:0:EMPTY";
            return this.messageHash;
        }

        String firstTwo = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");

        String firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "").toUpperCase();

        this.messageHash = firstTwo + ":" + messageNumber + ":" + firstWord + lastWord;
        return this.messageHash;
    }

    public String checkMessageLength() {
        if (messageText == null) {
            return "Message exceeds 250 characters by 0; please reduce the size.";
        }
        int length = messageText.length();
        if (length <= 250) {
            return "Message ready to send.";
        } else {
            int excess = length - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }

    // ========== SentMessage (updated for Part 3 arrays) ==========
    public String SentMessage(String choice) {
        createMessageHash(); // make sure hash exists

        if (choice.equals("1")) { // Send
            if (sentCount < sentMessages.length) {
                sentMessages[sentCount] = messageText;
                messageHashes[sentCount] = messageHash;
                messageIDs[sentCount] = messageID;
                sentCount++;
            }
            return "Message successfully sent.";
        } 
        else if (choice.equals("2")) { // Disregard
            if (disregardedCount < disregardedMessages.length) {
                disregardedMessages[disregardedCount] = messageText;
                disregardedCount++;
            }
            return "Press 0 to delete the message.";
        } 
        else if (choice.equals("3")) { // Store
            storeMessage();
            if (storedCount < storedMessages.length) {
                storedMessages[storedCount] = messageText;
                messageHashes[storedCount] = messageHash;
                messageIDs[storedCount] = messageID;
                storedCount++;
            }
            return "Message successfully stored.";
        }
        return "Invalid choice.";
    }

    // ========== Store to JSON ==========
    public void storeMessage() {
        try (FileWriter writer = new FileWriter("stored_messages.json", true)) {
            writer.write("{\n");
            writer.write("  \"messageID\": \"" + messageID + "\",\n");
            writer.write("  \"messageHash\": \"" + messageHash + "\",\n");
            writer.write("  \"recipient\": \"" + recipient + "\",\n");
            writer.write("  \"message\": \"" + messageText.replace("\"", "\\\"") + "\"\n");
            writer.write("},\n");
        } catch (IOException e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }

    // ========== PART 3 Methods ==========

    // a. Display all stored messages (sender + recipient)
    public String displayAllStoredMessages() {
        if (storedCount == 0) return "No stored messages.";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < storedCount; i++) {
            sb.append("Message ID: ").append(messageIDs[i])
              .append(" | Hash: ").append(messageHashes[i])
              .append(" | Message: ").append(storedMessages[i]).append("\n");
        }
        return sb.toString();
    }

    // b. Display the longest stored message
    public String displayLongestMessage() {
        if (storedCount == 0 && sentCount == 0) return "No messages found.";

        String longest = "";
        // Check stored
        for (int i = 0; i < storedCount; i++) {
            if (storedMessages[i] != null && storedMessages[i].length() > longest.length()) {
                longest = storedMessages[i];
            }
        }
        // Check sent
        for (int i = 0; i < sentCount; i++) {
            if (sentMessages[i] != null && sentMessages[i].length() > longest.length()) {
                longest = sentMessages[i];
            }
        }
        return longest.isEmpty() ? "No messages found." : longest;
    }

    // c. Search by Message ID
    public String searchByMessageID(String id) {
        for (int i = 0; i < sentCount; i++) {
            if (messageIDs[i] != null && messageIDs[i].equals(id)) {
                return "Message: " + sentMessages[i];
            }
        }
        for (int i = 0; i < storedCount; i++) {
            if (messageIDs[i] != null && messageIDs[i].equals(id)) {
                return "Message: " + storedMessages[i];
            }
        }
        return "Message ID not found.";
    }

    // d. Search all messages for a recipient
    public String searchByRecipient(String cell) {
        StringBuilder sb = new StringBuilder();
        boolean found = false;

        
        
        for (int i = 0; i < storedCount; i++) {
            if (storedMessages[i] != null) {
                sb.append(storedMessages[i]).append("\n");
                found = true;
            }
        }
        for (int i = 0; i < sentCount; i++) {
            if (sentMessages[i] != null) {
                sb.append(sentMessages[i]).append("\n");
                found = true;
            }
        }
        return found ? sb.toString() : "No messages found for that recipient.";
    }

    // e. Delete a message using Message Hash
    public String deleteByHash(String hash) {
        for (int i = 0; i < sentCount; i++) {
            if (messageHashes[i] != null && messageHashes[i].equals(hash)) {
                String deleted = sentMessages[i];
                // Shift elements
                for (int j = i; j < sentCount - 1; j++) {
                    sentMessages[j] = sentMessages[j + 1];
                    messageHashes[j] = messageHashes[j + 1];
                    messageIDs[j] = messageIDs[j + 1];
                }
                sentCount--;
                return "Message: \"" + deleted + "\" successfully deleted.";
            }
        }
        for (int i = 0; i < storedCount; i++) {
            if (messageHashes[i] != null && messageHashes[i].equals(hash)) {
                String deleted = storedMessages[i];
                for (int j = i; j < storedCount - 1; j++) {
                    storedMessages[j] = storedMessages[j + 1];
                    messageHashes[j] = messageHashes[j + 1];
                    messageIDs[j] = messageIDs[j + 1];
                }
                storedCount--;
                return "Message: \"" + deleted + "\" successfully deleted.";
            }
        }
        return "Hash not found.";
    }

    // f. Full Report
    public String displayFullReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== FULL REPORT =====\n\n");

        sb.append("--- SENT MESSAGES ---\n");
        for (int i = 0; i < sentCount; i++) {
            sb.append("Hash: ").append(messageHashes[i])
              .append(" | Message: ").append(sentMessages[i]).append("\n");
        }

        sb.append("\n--- STORED MESSAGES ---\n");
        for (int i = 0; i < storedCount; i++) {
            sb.append("Hash: ").append(messageHashes[i])
              .append(" | Message: ").append(storedMessages[i]).append("\n");
        }

        sb.append("\n--- DISREGARDED MESSAGES ---\n");
        for (int i = 0; i < disregardedCount; i++) {
            sb.append(disregardedMessages[i]).append("\n");
        }

        return sb.toString();
    }

    // Helper for tests
    public int getSentCount() { return sentCount; }
    public String[] getSentMessages() { return sentMessages; }
}