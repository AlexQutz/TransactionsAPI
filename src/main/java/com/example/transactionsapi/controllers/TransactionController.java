package com.example.transactionsapi.controllers;

import com.example.transactionsapi.models.Account;
import com.example.transactionsapi.models.Transaction;
import com.example.transactionsapi.services.AccountService;
import com.example.transactionsapi.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {

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


    @PostMapping("/transfer")
    public ResponseEntity<?> transferMoney(@RequestBody Transaction transaction) {
        try {
            Transaction result = transactionService.transferMoney(transaction);
            return ResponseEntity.ok("Transaction completed!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
