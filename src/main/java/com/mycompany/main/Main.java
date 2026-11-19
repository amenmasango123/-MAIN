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

    
    }
}
    
    