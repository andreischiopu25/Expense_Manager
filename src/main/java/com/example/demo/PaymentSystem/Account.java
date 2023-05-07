package com.example.demo.PaymentSystem;

import java.io.Serializable;

public class Account implements Serializable {

    private Integer balance;
    private final Integer accountId;

    public Account(Integer balance, Integer accountId) {
        this.balance = balance;
        this.accountId = accountId;
    }

    public Account(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getAccountId() {
        return accountId;
    }
    public void deposit(Integer amount) {
        Integer currentBalance = getBalance();
        setBalance(currentBalance + amount);
    }

    public boolean withdraw(Integer amount) {
        Integer currentBalance = getBalance();
        if (currentBalance >= amount) {
            setBalance(currentBalance - amount);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", accountId=" + accountId +
                '}';
    }
}
