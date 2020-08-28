package com.sysoiev.app.model;

public class Account {

    private Long id;
    private AccountStatus accountStatus;

    public Account(Long id) {
        this.id = id;
    }

    public Account(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Account(Long id, AccountStatus accountStatus) {
        this.id = id;
        this.accountStatus = accountStatus;
    }

    public Long getId() {
        return id;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    @Override
    public String toString() {
        return id + " " + accountStatus;
    }

}