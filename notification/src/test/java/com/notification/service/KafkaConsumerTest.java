package com.notification.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class KafkaConsumerTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private KafkaConsumer kafkaConsumer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConsumeMessage() {
        String message = "Test message";

        kafkaConsumer.consume(message);

        verify(notificationService, times(1)).sendNotification(message);
    }
}