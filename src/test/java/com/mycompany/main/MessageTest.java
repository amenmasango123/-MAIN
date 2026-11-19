/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 *Unit tests for Part 2 – Message class
 * @author Student
 */
public class MessageTest {

    @Test
    public void testSentMessagesArrayPopulated() {
        Message msg1 = new Message();
        msg1.setMessageText("Did you get the cake?");
        msg1.setRecipient("+27834557896");
        msg1.SentMessage("1");

        Message msg2 = new Message();
        msg2.setMessageText("It is dinner time!");
        msg2.setRecipient("0838884567");
        msg2.SentMessage("1");

        assertTrue(msg2.getSentCount() >= 2);
    }

    