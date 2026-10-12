/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Student
 */
public class LoginTest {

    @Test
    void testUsernameCorrectlyFormatted() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.checkUserName());
    }

    @Test
    void testUsernameIncorrectlyFormatted() {
        Login user = new Login("Kyle", "Smith", "kyle!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(user.checkUserName());
    }

    @Test
    void testPasswordMeetsComplexity() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.checkPasswordComplexity());
    }

    @Test
    void testPasswordDoesNotMeetComplexity() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "password", "+27838968976");
        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    void testCellPhoneCorrectlyFormatted() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.checkCellPhoneNumber());
    }

    @Test
    void testCellPhoneIncorrectlyFormatted() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "08966553");
        assertFalse(user.checkCellPhoneNumber());
    }

    @Test
    void testRegisterSuccess() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("Registration successful.", user.registerUser());
    }

    @Test
    void testRegisterUsernameFailMessage() {
        Login user = new Login("Kyle", "Smith", "kyle!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertEquals(
            "Username is not correctly formatted; please ensure that your username "
            + "contains an underscore and is no more than five characters in length.",
            user.registerUser()
        );
    }

    @Test
    void testRegisterPasswordFailMessage() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "password", "+27838968976");
        assertEquals(
            "Password is not correctly formatted; please ensure that the password "
            + "contains at least eight characters, a capital letter, a number, and a special character.",
            user.registerUser()
        );
    }

    @Test
    void testRegisterCellFailMessage() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "08966553");
        assertEquals(
            "Cell number is incorrectly formatted or does not contain an international code; "
            + "please correct the number and try again.",
            user.registerUser()
        );
    }

    @Test
    void testLoginSuccessful() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    void testLoginFailed() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(user.loginUser("kyl_1", "wrongpass"));
    }

    @Test
    void testReturnLoginStatusSuccess() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean success = user.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertEquals("Welcome Kyle, Smith it is great to see you.", user.returnLoginStatus(success));
    }

    @Test
    void testReturnLoginStatusFail() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean success = user.loginUser("kyl_1", "wrong");
        assertEquals("Username or password incorrect, please try again.", user.returnLoginStatus(success));
    }
}

