package com.example.demo.repository;

import com.example.demo.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    //Select * From tbl_expenses where expenseId =?
    Optional<Expense> findByExpenseId(String id);

    List<Expense>  findByNameContainingAndDateBetween(String keyword, Date startDate, Date endDate);

}
