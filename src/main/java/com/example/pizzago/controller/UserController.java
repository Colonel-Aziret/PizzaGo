package com.example.pizzago.controller;

import com.example.pizzago.exceptions.IncorrectPasswordException;
import com.example.pizzago.exceptions.UserNotFoundException;
import com.example.pizzago.model.User;
import com.example.pizzago.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("error", error != null);
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password) {
        try {
            if (userService.authenticateUser(email, password)) {
                return "redirect:/dashboard";
            } else {
                return "redirect:/login?error=true";
            }
        } catch (UserNotFoundException | IncorrectPasswordException e) {
            return "redirect:/login?error=true";
        }
    }


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        userService.registerUser(user);
        return "redirect:/login";
    }
}
