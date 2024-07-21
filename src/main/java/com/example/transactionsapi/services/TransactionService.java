package com.example.transactionsapi.services;

import com.example.transactionsapi.models.Account;
import com.example.transactionsapi.models.Transaction;
import com.example.transactionsapi.repositories.AccountRepository;
import com.example.transactionsapi.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MessageSource messageSource;

    public Transaction transferMoney(Transaction transaction) {

        // Validate the existence of accounts
        Optional<Account> sourceAccountOpt = accountRepository.findById(transaction.getSourceAccountId());
        Optional<Account> targetAccountOpt = accountRepository.findById(transaction.getTargetAccountId());

        if (!sourceAccountOpt.isPresent() || !targetAccountOpt.isPresent()) {
            throw new IllegalArgumentException(messageSource.getMessage("account.not.found", null, Locale.getDefault()));
        }

        Account sourceAccount = sourceAccountOpt.get();
        Account targetAccount = targetAccountOpt.get();

        // Prevent transfer between the same account
        if (sourceAccount.getId().equals(targetAccount.getId())) {
            throw new IllegalArgumentException(messageSource.getMessage("same.account.transfer", null, Locale.getDefault()));
        }

        // Handling insufficient balance to process money transfer
        if (sourceAccount.getBalance() < transaction.getAmount()) {
            throw new IllegalArgumentException(messageSource.getMessage("account.insufficient.balance", null, Locale.getDefault()));
        }

        completeTransfer(transaction, sourceAccount, targetAccount);

        // Save the transaction
        return transactionRepository.save(transaction);
    }

    private void completeTransfer(Transaction transaction, Account sourceAccount, Account targetAccount) {
        sourceAccount.setBalance(sourceAccount.getBalance() - transaction.getAmount());
        targetAccount.setBalance(targetAccount.getBalance() + transaction.getAmount());

        // Save the updated accounts
        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);
    }


}
