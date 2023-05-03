package com.example.pizzago.controller;

import com.example.pizzago.model.Order;
import com.example.pizzago.model.Pizza;
import com.example.pizzago.repository.OrderRepository;
import com.example.pizzago.repository.PizzaRepository;
import com.example.pizzago.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private OrderService orderService;

    @GetMapping("/orderList")
    public String orderList(Model model) {
        List<Pizza> pizzas = pizzaRepository.findAll();
        model.addAttribute("pizzas", pizzas);
        return "orderList";
    }

    @GetMapping("/orderForm")
    public ModelAndView orderForm() {
        ModelAndView modelAndView = new ModelAndView("orderForm");
        modelAndView.addObject("order", new Order());
        return modelAndView;
    }
}
