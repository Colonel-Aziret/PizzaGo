package com.example.pizzago.service;

import com.example.pizzago.enums.Role;
import com.example.pizzago.exceptions.IncorrectPasswordException;
import com.example.pizzago.exceptions.UserAlreadyExistsException;
import com.example.pizzago.exceptions.UserNotFoundException;
import com.example.pizzago.model.User;
import com.example.pizzago.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public User registerUser(User user) {
        // Проверяем, есть ли пользователь уже в БД
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists");
        }

        // Хешируем пароль и сохраняем пользователя в БД
        user.setRole(Role.CUSTOMER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User login(String email, String password) {
        // Ищем пользователя в БД по email
        User user = (User) userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));

        // Проверяем пароль
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IncorrectPasswordException("Incorrect password for user with email " + email);
        }

        // Возвращаем пользователя
        return user;
    }

    @Override
    public boolean authenticateUser(String email, String password) throws UserNotFoundException, IncorrectPasswordException {
        User user = (User) userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public User getUserByEmail(String email) {
        return (User) userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}
