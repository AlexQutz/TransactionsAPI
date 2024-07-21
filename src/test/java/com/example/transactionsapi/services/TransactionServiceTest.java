package com.example.transactionsapi.services;

import com.example.transactionsapi.models.Account;
import com.example.transactionsapi.models.Transaction;
import com.example.transactionsapi.repositories.AccountRepository;
import com.example.transactionsapi.repositories.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;

import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void transferMoney_happyPath() {
        Account sourceAccount = new Account();
        sourceAccount.setId(1L);
        sourceAccount.setBalance(1000.0);
        sourceAccount.setCurrency("GBP");

        Account targetAccount = new Account();
        targetAccount.setId(2L);
        targetAccount.setBalance(500.0);
        targetAccount.setCurrency("GBP");

        Transaction transaction = new Transaction();
        transaction.setSourceAccountId(sourceAccount.getId());
        transaction.setTargetAccountId(targetAccount.getId());
        transaction.setAmount(100.0);
        transaction.setCurrency("GBP");

        when(accountRepository.findById(sourceAccount.getId())).thenReturn(Optional.of(sourceAccount));
        when(accountRepository.findById(targetAccount.getId())).thenReturn(Optional.of(targetAccount));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction result = transactionService.transferMoney(transaction);

        assertEquals(900.0, sourceAccount.getBalance());
        assertEquals(600.0, targetAccount.getBalance());
        assertNotNull(result);
    }

    @Test
    void transferMoney_insufficientBalance() {
        Account sourceAccount = new Account();
        sourceAccount.setId(1L);
        sourceAccount.setBalance(50.0);
        sourceAccount.setCurrency("GBP");

        Account targetAccount = new Account();
        targetAccount.setId(2L);
        targetAccount.setBalance(500.0);
        targetAccount.setCurrency("GBP");

        Transaction transaction = new Transaction();
        transaction.setSourceAccountId(sourceAccount.getId());
        transaction.setTargetAccountId(targetAccount.getId());
        transaction.setAmount(100.0);
        transaction.setCurrency("GBP");

        when(accountRepository.findById(sourceAccount.getId())).thenReturn(Optional.of(sourceAccount));
        when(accountRepository.findById(targetAccount.getId())).thenReturn(Optional.of(targetAccount));
        when(messageSource.getMessage("account.insufficient.balance", null, Locale.getDefault()))
                .thenReturn("Insufficient balance in source account");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transactionService.transferMoney(transaction);
        });

        assertEquals("Insufficient balance in source account", exception.getMessage());
    }

    @Test
    void transferMoney_sameAccount() {
        Account sourceAccount = new Account();
        sourceAccount.setId(1L);
        sourceAccount.setBalance(1000.0);
        sourceAccount.setCurrency("GBP");

        Transaction transaction = new Transaction();
        transaction.setSourceAccountId(sourceAccount.getId());
        transaction.setTargetAccountId(sourceAccount.getId());
        transaction.setAmount(100.0);
        transaction.setCurrency("GBP");

        when(accountRepository.findById(sourceAccount.getId())).thenReturn(Optional.of(sourceAccount));
        when(messageSource.getMessage("same.account.transfer", null, Locale.getDefault()))
                .thenReturn("Cannot transfer money to the same account");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transactionService.transferMoney(transaction);
        });

        assertEquals("Cannot transfer money to the same account", exception.getMessage());
    }

    @Test
    void transferMoney_accountNotFound() {
        Transaction transaction = new Transaction();
        transaction.setSourceAccountId(1L);
        transaction.setTargetAccountId(2L);
        transaction.setAmount(100.0);
        transaction.setCurrency("GBP");

        when(accountRepository.findById(transaction.getSourceAccountId())).thenReturn(Optional.empty());
        when(accountRepository.findById(transaction.getTargetAccountId())).thenReturn(Optional.empty());
        when(messageSource.getMessage("account.not.found", null, Locale.getDefault()))
                .thenReturn("One or both accounts do not exist");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transactionService.transferMoney(transaction);
        });

        assertEquals("One or both accounts do not exist", exception.getMessage());
    }
}
