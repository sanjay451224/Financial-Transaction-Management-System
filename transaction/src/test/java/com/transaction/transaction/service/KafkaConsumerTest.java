package com.transaction.transaction.service;

import com.transaction.transaction.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class KafkaConsumerTest {

  
    @InjectMocks
    private KafkaConsumer kafkaConsumer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConsume() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAmount(100.0);
        transaction.setDescription("242a");

        kafkaConsumer.consume(transaction.getDescription());

    }
}