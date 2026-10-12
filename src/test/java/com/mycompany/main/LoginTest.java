/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import org.junit.Test;
import static org.junit.Assert.*;
  
/**
 * @author Student
 */
public class LoginTest {

    @Test
    public void testUsernameCorrectlyFormatted() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.checkUserName());
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        Login user = new Login("Kyle", "Smith", "kyle!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(user.checkUserName());
    }

    @Test
    public void testPasswordMeetsComplexity() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.checkPasswordComplexity());
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "password", "+27838968976");
        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testCellPhoneCorrectlyFormatted() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.checkCellPhoneNumber());
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "08966553");
        assertFalse(user.checkCellPhoneNumber());
    }

    @Test
    public void testRegisterSuccess() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("Registration successful.", user.registerUser());
    }

    @Test
    public void testRegisterUsernameFailMessage() {
        Login user = new Login("Kyle", "Smith", "kyle!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertEquals(
            "Username is not correctly formatted: please ensure that your username "
            + "contains an underscore and is no more than five characters in length.",
            user.registerUser()
        );
    }

    @Test
    public void testRegisterPasswordFailMessage() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "password", "+27838968976");
        assertEquals(
            "Password is not correctly formatted: please ensure that the password "
            + "contains at least eight characters, a capital letter, a number, and a special character.",
            user.registerUser()
        );
    }

    @Test
    public void testRegisterCellFailMessage() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "08966553");
        assertEquals(
            "Cell number is incorrectly formatted or does not contain an international code, "
            + "please correct the number and try again.",
            user.registerUser()
        );
    }
}
    

 