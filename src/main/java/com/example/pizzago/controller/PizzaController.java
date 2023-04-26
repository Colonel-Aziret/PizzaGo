package com.example.pizzago.controller;

import com.example.pizzago.model.Pizza;
import com.example.pizzago.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PizzaController {
    @Autowired
    PizzaRepository pizzaRepository;

    @GetMapping("/")
    public String showHomePage(Model model) {
        List<Pizza> pizzas = pizzaRepository.findAll();
        model.addAttribute("pizzas", pizzas);
        return "index";
    }
}
