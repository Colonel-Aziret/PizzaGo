package com.example.pizzago.service;

import com.example.pizzago.model.Pizza;
import com.example.pizzago.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaServiceImpl implements PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    @Override
    public Pizza savePizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }
}
