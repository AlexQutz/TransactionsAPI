package com.example.transactionsapi.controllers;

import com.example.transactionsapi.models.Account;
import com.example.transactionsapi.models.Transaction;
import com.example.transactionsapi.services.AccountService;
import com.example.transactionsapi.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTransaction(){
        return transactionService.getAllTransactions();
    }
}
