package com.example.transactionsapi.models;

import com.example.transactionsapi.repositories.AccountRepository;
import com.example.transactionsapi.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void run(String... args) throws Exception {
        Account account1 = new Account();
        account1.setBalance(1000.0);
        account1.setCurrency("GBP");
        account1.setCreatedAt(Instant.parse("2024-01-15T12:00:00Z"));
        accountRepository.save(account1);

        Account account2 = new Account();
        account2.setBalance(500.0);
        account2.setCurrency("GBP");
        account2.setCreatedAt(Instant.parse("2024-01-16T12:00:00Z"));
        accountRepository.save(account2);
        
        Transaction transaction1 = new Transaction();
        transaction1.setSourceAccountId(account1.getId());
        transaction1.setTargetAccountId(account2.getId());
        transaction1.setAmount(50.0);
        transaction1.setCurrency("GBP");
        transactionRepository.save(transaction1);

        Transaction transaction2 = new Transaction();
        transaction2.setSourceAccountId(account2.getId());
        transaction2.setTargetAccountId(account1.getId());
        transaction2.setAmount(100.0);
        transaction2.setCurrency("GBP");
        transactionRepository.save(transaction2);
    }
}