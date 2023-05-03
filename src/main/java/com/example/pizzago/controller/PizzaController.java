package com.example.pizzago.controller;

import com.example.pizzago.model.Pizza;
import com.example.pizzago.repository.PizzaRepository;
import com.example.pizzago.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private PizzaService pizzaService;

    public PizzaController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        List<Pizza> pizzas = pizzaRepository.findAll();
        model.addAttribute("pizzas", pizzas);
        return "index";
    }

    @GetMapping("/add_pizza")
    public ModelAndView addPizza() {
        ModelAndView modelAndView = new ModelAndView("addPizza");
        modelAndView.addObject("pizza", new Pizza());
        return modelAndView;
    }

    @PostMapping("/save_pizza")
    public String savePizza(@ModelAttribute(name = "pizza") Pizza pizza) {
        this.pizzaService.savePizza(pizza);
        return "index";
    }
}
