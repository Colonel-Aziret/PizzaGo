package com.example.pizzago.controller;

import com.example.pizzago.model.Order;
import com.example.pizzago.model.Pizza;
import com.example.pizzago.repository.PizzaRepository;
import com.example.pizzago.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    OrderService orderService;

    public OrderController(PizzaRepository pizzaRepository, OrderService orderService) {
        this.pizzaRepository = pizzaRepository;
        this.orderService = orderService;
    }

    @GetMapping("/order/new")
    public String showOrderForm(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("pizzas", pizzaRepository.findAll());
        return "orderForm";
    }

    @PostMapping("/order")
    public String createOrder(@ModelAttribute("order") Order order) {
        orderService.save(order);
        return "redirect:/";
    }
}
