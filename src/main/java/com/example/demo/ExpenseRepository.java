package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    //Select * From tbl_expenses where expenseId =?
    Optional<Expense> findByExpenseId(String id);

}
