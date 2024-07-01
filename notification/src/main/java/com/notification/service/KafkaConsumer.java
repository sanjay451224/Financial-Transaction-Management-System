package com.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    private NotificationService notificationService;

    @KafkaListener(topics = "transaction_topic", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
        notificationService.sendNotification(message);
    }
}
