package com.example.demo.service;

import com.example.demo.DTO.ExpenseDTO;
import com.example.demo.DTO.ExpenseFilterDTO;
import com.example.demo.entity.Expense;
import com.example.demo.repository.ExpenseRepository;
import com.example.demo.util.DateTimeUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExpenseService {


    private ExpenseRepository expenseRepository;
    private final ModelMapper modelMapper;

    private final UserService userService;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository, ModelMapper modelMapper, UserService userService) {
            this.expenseRepository = expenseRepository;
            this.modelMapper = modelMapper;
            this.userService = userService;
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
                //add the logged in user to the expense entity
                expense.setUser(userService.getLoggedInUser());
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

    public List<ExpenseDTO> getFilteredExpenses(ExpenseFilterDTO expenseFilterDTO) throws ParseException {

        String keyword = expenseFilterDTO.getKeyword();
        String sortBy = expenseFilterDTO.getSortBy();
        String startDate = expenseFilterDTO.getStartDate();
        String endDate = expenseFilterDTO.getEndDate();

        Date sDate= !startDate.isEmpty() ? DateTimeUtil.convertStringToDate(startDate): new Date(0);
        Date eDate = !endDate.isEmpty()? DateTimeUtil.convertStringToDate(endDate) : new Date(System.currentTimeMillis());

       List<Expense> list= expenseRepository.findByNameContainingAndDateBetween(keyword, sDate, eDate);
       List<ExpenseDTO> filteredList= list.stream().map(this::mapToDTO).collect(Collectors.toList());
       if (sortBy.equals("date")){
            // sort by date
           filteredList.sort((o1,o2) ->o2.getDate().compareTo(o1.getDate()));
       }else {
           filteredList.sort((o1,o2) -> o2.getAmount().compareTo(o1.getAmount()));
       }
       return filteredList;
    }

    public String totalExpenses (List<ExpenseDTO> expenses){
        BigDecimal sum = new BigDecimal(0);
        BigDecimal total = expenses.stream().map(x -> x.getAmount().add(sum))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
        return format.format(total).substring(1);
    }


}
