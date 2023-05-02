package com.example.pizzago.controller;

import com.example.pizzago.model.Order;
import com.example.pizzago.repository.PizzaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrderController {

    private final PizzaRepository pizzaRepository;

    private final OrderService orderService;

    public OrderController(PizzaRepository pizzaRepository, OrderService orderService) {
        this.pizzaRepository = pizzaRepository;
        this.orderService = orderService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
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

    @GetMapping("/order")
    public String showOrderList(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "order/list";
    }
}
