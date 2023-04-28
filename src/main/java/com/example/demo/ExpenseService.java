package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {


    private final ExpenseRepository expenseRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository, ModelMapper modelMapper) {
            this.expenseRepository = expenseRepository;
            this.modelMapper = modelMapper;
    }

    public List<ExpenseDTO> getAllExpense(){
           List<Expense> list= expenseRepository.findAll();
           List<ExpenseDTO> expenseList = list.stream().map(this::mapToDTO).collect(Collectors.toList());
           return expenseList;
        }
        private ExpenseDTO mapToDTO (Expense expense){
          ExpenseDTO expenseDTO = new ExpenseDTO();
          expenseDTO.setId(expense.getId());
          expenseDTO.setName(expense.getName());
          expenseDTO.setDescription(expense.getDescription());
          expenseDTO.setAmount(expense.getAmount());
          expenseDTO.setDate(expense.getDate());
          expenseDTO.setDateString(DateTimeUtil.convertDateToString((Date) expenseDTO.getDate()));
          return expenseDTO;
        }


}
