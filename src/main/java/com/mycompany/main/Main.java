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

            if (loginSuccessful) {
                System.out.println("\nWelcome to QuickChat.");

                boolean running = true;
                while (running) {
                    System.out.println("\nPlease choose an option:");
                    System.out.println("1) Send Messages");
                    System.out.println("2) Show recently sent messages");
                    System.out.println("3) Quit");
                    System.out.println("4) Stored Messages");
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
                        case "4":
                            storedMessagesMenu(input);
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                }
            }
        }

        input.close();
    }

    // ========== Send Messages (Part 2) ==========
    private static void sendMessages(Scanner input) {
        System.out.print("\nHow many messages do you want to send? ");
        int numMessages = Integer.parseInt(input.nextLine());

        for (int i = 0; i < numMessages; i++) {
            System.out.println("\n----- Message " + (i + 1) + " of " + numMessages + " -----");

            Message msg = new Message();
            msg.setMessageNumber(i);

            System.out.print("Enter recipient cell number (e.g. +27718693002): ");
            String recipient = input.nextLine();
            msg.setRecipient(recipient);
            System.out.println(msg.checkRecipientCell());

            System.out.print("Enter your message (max 250 characters): ");
            String text = input.nextLine();
            msg.setMessageText(text);
            System.out.println(msg.checkMessageLength());

            String hash = msg.createMessageHash();
            System.out.println("Message Hash: " + hash);
            System.out.println("Message ID generated: " + msg.getMessageID());

            System.out.println("\nWhat would you like to do with this message?");
            System.out.println("1) Send Message");
            System.out.println("2) Disregard Message");
            System.out.println("3) Store Message to send later");
            System.out.print("Enter choice (1-3): ");
            String action = input.nextLine();

            String result = msg.SentMessage(action);
            System.out.println(result);

            if (result.equals("Message successfully sent.")) {
                System.out.println("\n----- Message Details -----");
                System.out.println("Message ID: " + msg.getMessageID());
                System.out.println("Message Hash: " + msg.getMessageHash());
                System.out.println("Recipient: " + msg.getRecipient());
                System.out.println("Message: " + msg.getMessageText());
            }
        }
    }

    // ========== PART 3 – Stored Messages Menu ==========
    private static void storedMessagesMenu(Scanner input) {
        Message helper = new Message();
        boolean back = false;

        while (!back) {
            System.out.println("\n===== STORED MESSAGES MENU =====");
            System.out.println("a) Display all stored messages");
            System.out.println("b) Display the longest message");
            System.out.println("c) Search by Message ID");
            System.out.println("d) Search by Recipient");
            System.out.println("e) Delete a message using Hash");
            System.out.println("f) Display Full Report");
            System.out.println("g) Back to main menu");
            System.out.print("Enter your choice: ");

            String option = input.nextLine().toLowerCase();

            switch (option) {
                case "a":
                    System.out.println(helper.displayAllStoredMessages());
                    break;
                case "b":
                    System.out.println("Longest message:\n" + helper.displayLongestMessage());
                    break;
                case "c":
                    System.out.print("Enter Message ID to search: ");
                    String id = input.nextLine();
                    System.out.println(helper.searchByMessageID(id));
                    break;
                case "d":
                    System.out.print("Enter recipient number: ");
                    String cell = input.nextLine();
                    System.out.println(helper.searchByRecipient(cell));
                    break;
                case "e":
                    System.out.print("Enter Message Hash to delete: ");
                    String hash = input.nextLine();
                    System.out.println(helper.deleteByHash(hash));
                    break;
                case "f":
                    System.out.println(helper.displayFullReport());
                    break;
                case "g":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
    
    