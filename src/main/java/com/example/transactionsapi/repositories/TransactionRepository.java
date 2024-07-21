package com.example.transactionsapi.repositories;

import com.example.transactionsapi.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
