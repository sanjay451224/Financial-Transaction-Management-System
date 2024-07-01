package com.transaction.transaction.service;

import com.transaction.transaction.model.Transaction;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class KafkaProducerTest {

    @Mock
    private KafkaTemplate<String, Transaction> kafkaTemplate;

    @InjectMocks
    private KafkaProducer kafkaProducer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendMessage() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAmount(100.0);
        transaction.setDescription("324");

        ListenableFuture<SendResult<String, Transaction>> future = mock(ListenableFuture.class);
        when(kafkaTemplate.send(any(ProducerRecord.class))).thenReturn((CompletableFuture) future);

        kafkaProducer.sendMessage(transaction.getDescription());

        verify(kafkaTemplate, times(1)).send(any(ProducerRecord.class));
    }
}