package com.example.transactionsapi.controllers;

import com.example.transactionsapi.models.Transaction;
import com.example.transactionsapi.services.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private TransactionController transactionController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }

    @Test
    void transferMoney_happyPath() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setSourceAccountId(1L);
        transaction.setTargetAccountId(2L);
        transaction.setAmount(100.0);
        transaction.setCurrency("GBP");

        when(transactionService.transferMoney(any(Transaction.class))).thenReturn(transaction);
        when(messageSource.getMessage("success.message", null, Locale.getDefault()))
                .thenReturn("Transaction completed!");

        mockMvc.perform(post("/api/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"sourceAccountId\":1,\"targetAccountId\":2,\"amount\":100.0,\"currency\":\"GBP\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Transaction completed!"));
    }

    @Test
    void transferMoney_insufficientBalance() throws Exception {
        when(transactionService.transferMoney(any(Transaction.class)))
                .thenThrow(new IllegalArgumentException("Insufficient balance in source account"));

        mockMvc.perform(post("/api/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"sourceAccountId\":1,\"targetAccountId\":2,\"amount\":1000.0,\"currency\":\"GBP\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Insufficient balance in source account"));
    }

    @Test
    void transferMoney_sameAccount() throws Exception {
        when(transactionService.transferMoney(any(Transaction.class)))
                .thenThrow(new IllegalArgumentException("Cannot transfer money to the same account"));

        mockMvc.perform(post("/api/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"sourceAccountId\":1,\"targetAccountId\":1,\"amount\":100.0,\"currency\":\"GBP\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Cannot transfer money to the same account"));
    }

    @Test
    void transferMoney_accountNotFound() throws Exception {
        when(transactionService.transferMoney(any(Transaction.class)))
                .thenThrow(new IllegalArgumentException("One or both accounts do not exist"));

        mockMvc.perform(post("/api/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"sourceAccountId\":1,\"targetAccountId\":2,\"amount\":100.0,\"currency\":\"GBP\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("One or both accounts do not exist"));
    }
}






