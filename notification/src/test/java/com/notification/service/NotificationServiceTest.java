package com.notification.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class NotificationServiceTest {

    private NotificationService notificationService = new NotificationService();

    @Test
    public void testSendNotification() {
        String message = "Test message";

        assertDoesNotThrow(() -> notificationService.sendNotification(message));
    }
}