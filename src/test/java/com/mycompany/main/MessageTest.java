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
    
    // ========== Message Length Tests ==========
    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message();
        msg.setMessageText("Hi Amen, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        Message msg = new Message();
        // Create a message longer than 250 characters
        String longMessage = "a".repeat(260);
        msg.setMessageText(longMessage);
        String result = msg.checkMessageLength();
        assertTrue(result.contains("Message exceeds 250 characters by"));
    }

    // ========== Recipient Tests ==========
    @Test
    public void testRecipientCorrectlyFormatted() {
        Message msg = new Message();
        msg.setRecipient("+27718693002");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientIncorrectlyFormatted() {
        Message msg = new Message();
        msg.setRecipient("08575975889"); // missing +
        assertEquals(
            "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
            msg.checkRecipientCell()
        );
    }

    // ========== Message Hash Test (exact data from brief) ==========
    @Test
    public void testMessageHashCorrect() {
        Message msg = new Message();
        msg.setMessageID("0012345678");          // first two digits = 00
        msg.setMessageNumber(0);
        msg.setMessageText("Hi Amen, can you join us for dinner tonight?");
        String hash = msg.createMessageHash();
        assertEquals("00:0:HITONIGHT", hash);
    }

    // ========== Message ID Test ==========
    @Test
    public void testMessageIDCreated() {
        Message msg = new Message();
        assertNotNull(msg.getMessageID());
        assertTrue(msg.checkMessageID());
        assertEquals(10, msg.getMessageID().length());
    }

    // ========== SentMessage Options ==========
    @Test
    public void testSentMessageSend() {
        Message msg = new Message();
        msg.setMessageText("Test message");
        msg.createMessageHash();
        assertEquals("Message successfully sent.", msg.SentMessage("1"));
    }

    @Test
    public void testSentMessageDisregard() {
        Message msg = new Message();
        assertEquals("Press 0 to delete the message.", msg.SentMessage("2"));
    }

    @Test
    public void testSentMessageStore() {
        Message msg = new Message();
        msg.setMessageText("Test message");
        msg.createMessageHash();
        assertEquals("Message successfully stored.", msg.SentMessage("3"));
    }
}

