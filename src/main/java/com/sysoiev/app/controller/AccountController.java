package com.sysoiev.app.controller;

import com.sysoiev.app.model.Account;
import com.sysoiev.app.repository.AccountRepository;
import com.sysoiev.app.repository.jdbc.JdbcAccountRepository;

import java.util.List;

public class AccountController {

    private AccountRepository accountRepository = new JdbcAccountRepository();


    public List<Account> printAll() {
        return accountRepository.getAll();
    }

    public void saveAccount(Account newAccount) {
        accountRepository.save(newAccount);
    }

    public void deleteAccount(Long index) {
        accountRepository.deleteById(index);
    }

    public void updateAccount(Account updateAccount) {
        accountRepository.update(updateAccount);

    }

    public Account getValueByIndex(Long index) {
        return accountRepository.getById(index);
    }
}