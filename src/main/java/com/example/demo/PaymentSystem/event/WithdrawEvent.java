package com.example.demo.PaymentSystem.event;

import com.example.demo.PaymentSystem.domain.Transaction;

public class WithdrawEvent extends Event {
        public WithdrawEvent(Transaction transaction) {
            super(transaction);
        }
 }
