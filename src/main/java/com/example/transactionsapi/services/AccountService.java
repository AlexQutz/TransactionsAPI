package com.example.transactionsapi.services;

import com.example.transactionsapi.models.Account;
import com.example.transactionsapi.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;


    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }


}
