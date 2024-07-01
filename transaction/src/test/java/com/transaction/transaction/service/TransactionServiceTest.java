package com.transaction.transaction.service;

import com.transaction.transaction.model.Transaction;
import com.transaction.transaction.repository.TransectionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TransactionServiceTest {

    @Mock
    private TransectionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTransaction() {
        Transaction transaction = new Transaction();
        transaction.setAmount(100.0);

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction createdTransaction = transactionService.createTransaction(transaction);

        assertEquals(100.0, createdTransaction.getAmount());
    }

    @Test
    public void testGetTransactionById() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAmount(100.0);

        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));

        Transaction foundTransaction = transactionService.getTransaction(1L);

        assertEquals(100.0, foundTransaction.getAmount());
    }
}