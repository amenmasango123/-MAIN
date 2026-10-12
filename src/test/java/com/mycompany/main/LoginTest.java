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

 