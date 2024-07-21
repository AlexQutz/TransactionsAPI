package com.example.transactionsapi.services;

import com.example.transactionsapi.models.Transaction;
import com.example.transactionsapi.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }


}
