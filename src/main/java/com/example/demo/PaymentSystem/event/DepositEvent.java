package com.example.demo.PaymentSystem.event;

import com.example.demo.PaymentSystem.domain.Transaction;

public class DepositEvent extends Event {
    public DepositEvent(Transaction transaction) {
        super(transaction);
    }

}
