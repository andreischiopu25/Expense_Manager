package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExpenseService {


    private  ExpenseRepository expenseRepository;
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
          expenseDTO.setExpenseId(expense.getExpenseId());
          expenseDTO.setName(expense.getName());
          expenseDTO.setDescription(expense.getDescription());
          expenseDTO.setAmount(expense.getAmount());
          expenseDTO.setDate(expense.getDate());
          expenseDTO.setDateString(DateTimeUtil.convertDateToString((Date) expenseDTO.getDate()));
          return expenseDTO;
        }

     public ExpenseDTO saveExpenseDetails(ExpenseDTO expenseDTO) throws ParseException {
                //map the DTO to entity

                Expense expense = mapToEntity (expenseDTO);

                //Save the entity to Database
                expense = expenseRepository.save(expense);
                //map the entity to DTO
               return mapToDTO(expense);
    }

    private Expense mapToEntity(ExpenseDTO expenseDTO) throws ParseException {
        //map the DTO entity
            Expense expense = new Expense();
            expense.setId(expenseDTO.getId());
            expense.setName(expenseDTO.getName());
            expense.setDescription(expenseDTO.getDescription());
            expense.setAmount (expenseDTO.getAmount());
        //generate the expense id
            if (expense.getId() == null){
                expense.setExpenseId(UUID.randomUUID().toString());
            }else expense.setExpenseId(expenseDTO.getExpenseId());
        //set the expense date
            expense.setDate(DateTimeUtil.convertStringToDate(expenseDTO.getDateString()));

        return expense;
    }

    public void deleteExpense(String id){
        Expense existingExpense = getExpense(id);
        expenseRepository.delete(existingExpense);
    }

    public ExpenseDTO getExpenseById (String id){
        Expense existingExpense = getExpense(id);
        return mapToDTO(existingExpense);
    }

    public Expense getExpense(String id){
        return expenseRepository.findByExpenseId(id).orElseThrow( ()->new RuntimeException("Expense not found for the ID"));
    }

}
