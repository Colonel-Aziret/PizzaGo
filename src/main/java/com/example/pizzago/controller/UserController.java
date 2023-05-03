package com.example.pizzago.controller;

import com.example.pizzago.model.User;
import com.example.pizzago.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                                  @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Incorrect login or password");
            model.setViewName("/login");
        }
        if (logout != null) {
            model.addObject("logout", "Logged out successfully.");
            model.setViewName("/login");
        }
        return "redirect:/";
    }

    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }


    @PostMapping("/registration")
    public String registration(@ModelAttribute(name = "user") User user) {
        try {
            ModelAndView model = new ModelAndView();
            this.userService.save(user);
            return "login";
        } catch (Exception e) {
            return "registration";
        }
    }

    @GetMapping("/adminMain")
    public String adminMain() {
        return "adminMain";
    }
}
