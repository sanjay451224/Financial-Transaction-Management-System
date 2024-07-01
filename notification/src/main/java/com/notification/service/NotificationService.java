package com.notification.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendNotification(String message) {
        // Logic to send notification (e.g., email, SMS)
        System.out.println("Sending notification: " + message);
    }
}
