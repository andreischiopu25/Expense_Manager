package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ExpenseController {
    // Aici injectam expenseService si apelam metode din clasa fac o

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }


    @GetMapping("/expenses")
    public String showExpenseList(Model model){
        model.addAttribute("expenses", expenseService.getAllExpense());
        return "expenses-list";
    }

    @GetMapping("/getall")
    public List<ExpenseDTO> showAllExpense(){
        return  expenseService.getAllExpense();
    }



}
