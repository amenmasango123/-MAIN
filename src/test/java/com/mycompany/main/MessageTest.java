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

    @Test
    public void testDisplayLongestMessage() {
        Message msg = new Message();
        msg.setMessageText("Where are you? You are late! I have asked you to be on time.");
        msg.setRecipient("+27838884567");
        msg.SentMessage("3"); // store

        String longest = msg.displayLongestMessage();
        assertTrue(longest.contains("Where are you?"));
    }

    @Test
    public void testSearchByMessageID() {
        Message msg = new Message();
        msg.setMessageID("0838884567");
        msg.setMessageText("It is dinner time!");
        msg.SentMessage("1");

        String result = msg.searchByMessageID("0838884567");
        assertTrue(result.contains("It is dinner time!"));
    }

    @Test
    public void testSearchByRecipient() {
        Message msg = new Message();
        msg.setRecipient("+27838884567");
        msg.setMessageText("Ok, I am leaving without you.");
        msg.SentMessage("3");

        String result = msg.searchByRecipient("+27838884567");
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteByHash() {
        Message msg = new Message();
        msg.setMessageText("Where are you? You are late! I have asked you to be on time.");
        msg.createMessageHash();
        String hash = msg.getMessageHash();
        msg.SentMessage("1");

        String result = msg.deleteByHash(hash);
        assertTrue(result.contains("successfully deleted"));
    }

    @Test
    public void testFullReport() {
        Message msg = new Message();
        String report = msg.displayFullReport();
        assertNotNull(report);
        assertTrue(report.contains("FULL REPORT"));
    }
}