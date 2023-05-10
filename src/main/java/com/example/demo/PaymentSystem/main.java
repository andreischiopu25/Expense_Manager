package com.example.demo.PaymentSystem;

import com.example.demo.PaymentSystem.domain.Account;
import org.springframework.util.SerializationUtils;

import java.util.*;
import java.util.stream.Collectors;

public class main {
    public static void main(String[] args) {
  //      Account a1= new Account(10_000_000, 101);
      /*  Bank bank = Bank.initializeBank();
        Bank singleThreadedBank = (Bank) SerializationUtils.clone(bank);
        Bank multiThreadedBank =  SerializationUtils.clone(bank);
        System.out.println("Total no. of transactions: " + Bank.TOTAL_TRANSACTIONS);
        System.out.println("Total no. of accounts: " + Bank.TOTAL_ACCOUNTS);
        System.out.println("Running transaction history in multiple threads...");
        long timerThreaded = multiThreadedBank.process(new ThreadRunner());
        System.out.println("Elapsed time for multi-thread solution: " + timerThreaded);*/

        List<Double> list = new ArrayList<>();
        list.add(234.6);
        list.add(2454.6);
        list.add(24.6);
        list.add(34.6);
        list.add(2.6);
        list.add(239.6);

        List<Double> list2 =list.stream().collect(Collectors.toList());
        Collections.sort(list2);
        System.out.println(Arrays.toString(list2.toArray()));







    }
}
