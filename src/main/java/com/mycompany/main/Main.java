/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;
import java.util.Scanner;
/**
 *
 * @author Student
 */
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("      CHAT APP REGISTRATION      ");
        System.out.println("================================");

        System.out.print("Enter your first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = input.nextLine();

        System.out.print("Enter a username: ");
        String username = input.nextLine();

        System.out.print("Enter a password: ");
        String password = input.nextLine();

        System.out.print("Enter your South African cell phone number: ");
        String cellPhoneNumber = input.nextLine();

        Login user = new Login(firstName, lastName, username, password, cellPhoneNumber);

        System.out.println("\n======== REGISTRATION ========");
        String registrationMessage = user.registerUser();
        System.out.println(registrationMessage);

        if (registrationMessage.equals("Registration successful.")) {
            System.out.println("\n======== LOGIN ========");
            System.out.print("Enter your username: ");
            String enteredUsername = input.nextLine();
            System.out.print("Enter your password: ");
            String enteredPassword = input.nextLine();

            boolean loginSuccessful = user.loginUser(enteredUsername, enteredPassword);
            System.out.println(user.returnLoginStatus(loginSuccessful));

            // ========== PART 2 STARTS HERE ==========
            if (loginSuccessful) {
                System.out.println("\nWelcome to QuickChat.");

                boolean running = true;
                while (running) {
                    System.out.println("\nPlease choose an option:");
                    System.out.println("1) Send Messages");
                    System.out.println("2) Show recently sent messages");
                    System.out.println("3) Quit");
                    System.out.print("Enter your choice: ");

                    String choice = input.nextLine();

                    switch (choice) {
                        case "1":
                            sendMessages(input);
                            break;
                        case "2":
                            System.out.println("Coming Soon.");
                            break;
                        case "3":
                            System.out.println("Goodbye!");
                            running = false;
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                }
            }
        }

        input.close();
    }

    // ========== Method that handles sending messages ==========
    private static void sendMessages(Scanner input) {
        System.out.print("\nHow many messages do you want to send? ");
        int numMessages = Integer.parseInt(input.nextLine());

        for (int i = 0; i < numMessages; i++) {
            System.out.println("\n----- Message " + (i + 1) + " of " + numMessages + " -----");

            Message msg = new Message();
            msg.setMessageNumber(i);   // starts from 0 as required by the brief

            // Recipient
            System.out.print("Enter recipient cell number (e.g. +27718693002): ");
            String recipient = input.nextLine();
            msg.setRecipient(recipient);
            System.out.println(msg.checkRecipientCell());

            // Message text
            System.out.print("Enter your message (max 250 characters): ");
            String text = input.nextLine();
            msg.setMessageText(text);
            System.out.println(msg.checkMessageLength());

            // Create and show the hash
            String hash = msg.createMessageHash();
            System.out.println("Message Hash: " + hash);
            System.out.println("Message ID generated: " + msg.getMessageID());

            // Ask what to do with the message
            System.out.println("\nWhat would you like to do with this message?");
            System.out.println("1) Send Message");
            System.out.println("2) Disregard Message");
            System.out.println("3) Store Message to send later");
            System.out.print("Enter choice (1-3): ");
            String action = input.nextLine();

            String result = msg.SentMessage(action);
            System.out.println(result);

            // If the message was sent, show full details
            if (result.equals("Message successfully sent.")) {
                System.out.println("\n----- Message Details -----");
                System.out.println("Message ID: " + msg.getMessageID());
                System.out.println("Message Hash: " + msg.getMessageHash());
                System.out.println("Recipient: " + msg.getRecipient());
                System.out.println("Message: " + msg.getMessageText());
            }
        }

        // total messages sent
        Message temp = new Message();
        System.out.println("\nTotal messages sent: " + temp.returnTotalMessages());
    }
}
    
    