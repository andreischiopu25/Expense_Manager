package com.example.demo;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/login", "/"})
    public String showLoginPage(){
        return "login";
    }
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") UserDTO userDTO,
                           BindingResult result,
                           Model model) {
        System.out.println("Printing the user details:"+userDTO);
        if (result.hasErrors()) {
            return "register";
        }
        userService.save(userDTO);
        model.addAttribute("successMsg", true);
        return "login";
    }

}
