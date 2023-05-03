package com.example.pizzago.service;

import com.example.pizzago.exceptions.IncorrectPasswordException;
import com.example.pizzago.exceptions.UserNotFoundException;
import com.example.pizzago.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User registerUser(User user);

    User login(String email, String password);

    boolean authenticateUser(String email, String password) throws UserNotFoundException, IncorrectPasswordException;

    User getUserByEmail(String email);

    User save(User user);
}
