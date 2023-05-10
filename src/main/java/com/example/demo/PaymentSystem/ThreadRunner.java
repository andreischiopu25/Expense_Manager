package com.example.demo.PaymentSystem;

import com.example.demo.PaymentSystem.domain.Account;
import com.example.demo.PaymentSystem.domain.Transaction;
import com.example.demo.PaymentSystem.event.DepositEvent;
import com.example.demo.PaymentSystem.event.Event;
import com.example.demo.PaymentSystem.event.WithdrawEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadRunner implements TransactionHistoryRunner {
    @Override
    public void run(Bank bank) {
        List<Thread> threads = new ArrayList<Thread>();
        CountDownLatch counter = new CountDownLatch(bank.getTransactions().length);
        Map<Integer, BlockingQueue<Event>> queues = new HashMap<Integer, BlockingQueue<Event>>();
/**
 * Create threads and event queue for each thread
 */

        for(Account acc: bank.getAccounts()) {
            BlockingQueue q = new LinkedBlockingQueue();
            queues.put(acc.getAccountId(), q);

            Thread t = new Thread(new BankWorker(counter, q));
            t.start();
            threads.add(t);
        }
        /**
         * Thread consumes Event objects.
         *
         * If instance of Event is WithdrawEvent, then thread immediately
         * transfers money to another account . Otherwise, thread waits
         * for money.
         */
        for(Transaction t: bank.getTransactions()) {
            BlockingQueue fromQueue = queues.get(t.getFrom().getAccountId());
            BlockingQueue toQueue = queues.get(t.getTo().getAccountId());

            fromQueue.add(new WithdrawEvent(t));
            toQueue.add(new DepositEvent(t));
        }

        try {
            counter.await();
        } catch (InterruptedException e) {}

        for(Thread t: threads) {
            t.interrupt();
        }
    }
}
