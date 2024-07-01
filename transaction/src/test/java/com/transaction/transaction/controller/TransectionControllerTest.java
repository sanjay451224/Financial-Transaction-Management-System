package com.transaction.transaction.controller;

import com.transaction.transaction.model.Transaction;
import com.transaction.transaction.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TransactionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransectionController transactionController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }

    @Test
    public void testCreateTransaction() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAmount(100.0);

        when(transactionService.createTransaction(any(Transaction.class))).thenReturn(transaction);

        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"amount\": 100.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(100.0));
    }

    @Test
    public void testGetTransactionById() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAmount(100.0);

        when(transactionService.getTransaction(1L)).thenReturn(transaction);

        mockMvc.perform(get("/api/transactions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(100.0));
    }
}