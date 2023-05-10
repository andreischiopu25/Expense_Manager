package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.ParseException;
import java.util.List;

@Controller
public class ExpenseFilterController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseFilterController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/filterExpenses")
    public String filterExpenses(@ModelAttribute("filter")ExpenseFilterDTO expenseFilterDTO, Model model) throws ParseException {
        System.out.println("Printing the filter dto:"+expenseFilterDTO);
        List<ExpenseDTO> list= expenseService.getFilteredExpenses(expenseFilterDTO);
        model.addAttribute("expenses", list);
        return "expenses-list";
    }

}
