package com.example.transactionsapi.controllers;

import com.example.transactionsapi.models.Account;
import com.example.transactionsapi.models.Transaction;
import com.example.transactionsapi.services.AccountService;
import com.example.transactionsapi.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private MessageSource messageSource;

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
            transactionService.transferMoney(transaction);
            return ResponseEntity.ok().body(messageSource.getMessage("success.message" , null , Locale.getDefault()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
