package com.example.pizzago.service;

import com.example.pizzago.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    User registerUser(User user);
    User login(String email, String password);
    boolean authenticateUser(String email, String password);

    User getUserByEmail(String email);
}

