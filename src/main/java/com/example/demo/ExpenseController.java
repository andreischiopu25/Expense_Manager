package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
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
        model.addAttribute("filter", new ExpenseFilterDTO());
        return "expenses-list";
    }

   @GetMapping("/createExpense")
    public String showExpenseForm(Model model){
        model.addAttribute("expense", new ExpenseDTO());
        return "expense-form";
    }

    @PostMapping("/saveOrUpdateExpense")
    public String saveOrUpdateExpenseDetails(@ModelAttribute("expense") ExpenseDTO expenseDTO) throws ParseException {
        System.out.println("Printing the Expense DTO: "+expenseDTO);
        expenseService.saveExpenseDetails(expenseDTO);
        return "redirect:/expenses";
    }
    @GetMapping("/deleteExpense")
    public String deleteExpense(@RequestParam String id){

        System.out.println("Printing the expense Id: "+id);
        expenseService.deleteExpense(id);
        return "redirect:/expenses";
    }

    @GetMapping("/updateExpense")
    public String updateExpense(@RequestParam String id, Model model) throws ParseException{
            System.out.println("Printing the expense Id inside update method:" +id);
            ExpenseDTO expense =expenseService.getExpenseById(id);
            model.addAttribute("expense", expense);
            return "expense-form";
    }


  /*  @GetMapping("/getAll")
    public List<ExpenseDTO> showAllExpense(){
        return  expenseService.getAllExpense();
    }*/


}
