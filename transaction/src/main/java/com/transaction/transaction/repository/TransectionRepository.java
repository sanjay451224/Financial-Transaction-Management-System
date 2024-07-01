package com.transaction.transaction.repository;

import com.transaction.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransectionRepository extends JpaRepository<Transaction, Long> {
}
