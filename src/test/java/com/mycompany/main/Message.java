/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
/**
 *
 * @author Student
 */
public class Message {
  
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    // Keep track of all sent messages
    private static List<Message> sentMessages = new ArrayList<>();
    private static int totalMessagesSent = 0;

    // Constructor – generates a random 10-digit Message ID
    public Message() {
        this.messageID = generateMessageID();
    }

    // Generate random 10-digit ID (as String so leading zeros are kept)
    private String generateMessageID() {
        Random random = new Random();
        long number = 1000000000L + (long)(random.nextDouble() * 9000000000L);
        return String.valueOf(number);
    }

    // ========== Getters and Setters ==========
    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public void setMessageNumber(int messageNumber) {
        this.messageNumber = messageNumber;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageHash() {
        return messageHash;
    }

    // ========== Required Methods from the brief ==========

    // 1. checkMessageID
    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    // 2. checkRecipientCell
    public String checkRecipientCell() {
        if (recipient == null) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
        // Must start with + and have international code + number (max 12 characters total is common)
        if (Pattern.matches("^\\+[0-9]{10,12}$", recipient)) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    // 3. createMessageHash
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

    // 4. SentMessage (this is the method that handles Send / Disregard / Store)
    public String SentMessage(String choice) {
        if (choice.equals("1")) {
            sentMessages.add(this);
            totalMessagesSent++;
            return "Message successfully sent.";
        } else if (choice.equals("2")) {
            return "Press 0 to delete the message.";
        } else if (choice.equals("3")) {
            storeMessage(); // we will write this method next
            return "Message successfully stored.";
        }
        return "Invalid choice.";
    }

    // 5. printMessages
    public String printMessages() {
        if (sentMessages.isEmpty()) {
            return "No messages sent yet.";
        }
        StringBuilder sb = new StringBuilder();
        for (Message msg : sentMessages) {
            sb.append("Message ID: ").append(msg.messageID).append("\n");
            sb.append("Message Hash: ").append(msg.messageHash).append("\n");
            sb.append("Recipient: ").append(msg.recipient).append("\n");
            sb.append("Message: ").append(msg.messageText).append("\n\n");
        }
        return sb.toString();
    }

    // 6. returnTotalMessages
    public int returnTotalMessages() {
        return totalMessagesSent;
    }

    // 7. storeMessage 
    public void storeMessage() {
        // Temporary – just prints for now
        System.out.println("Message stored temporarily (JSON will be added later).");
    }

    // Extra helper – check message length
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
}
    

