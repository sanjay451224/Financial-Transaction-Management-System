package com.transaction.transaction.service;

import com.transaction.transaction.model.Transaction;
import com.transaction.transaction.repository.TransectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransectionRepository transactionRepository;

    @Autowired
    private KafkaProducer kafkaProducer;

    public Transaction createTransaction(Transaction transaction) {
        Transaction savedTransaction = transactionRepository.save(transaction);
        kafkaProducer.sendMessage("Transaction created: " + savedTransaction.getId());
        return savedTransaction;
    }

    public Transaction updateTransaction(Long id, Transaction transaction) {
        Transaction existingTransaction = transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
        existingTransaction.setAmount(transaction.getAmount());
        existingTransaction.setDate(transaction.getDate());
        existingTransaction.setDescription(transaction.getDescription());
        Transaction updatedTransaction = transactionRepository.save(existingTransaction);
        kafkaProducer.sendMessage("Transaction updated: " + updatedTransaction.getId());
        return updatedTransaction;
    }

    public Transaction getTransaction(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
