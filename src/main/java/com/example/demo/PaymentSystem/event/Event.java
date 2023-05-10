package com.example.demo.PaymentSystem.event;

import com.example.demo.PaymentSystem.domain.Transaction;

public class Event {
    private final Transaction transaction;

    Event(Transaction transaction) {
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }
}
