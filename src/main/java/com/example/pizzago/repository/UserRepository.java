package com.example.pizzago.repository;

import com.example.pizzago.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    Optional<Object> findByEmail(String email);

    User findFirstByLogin(String login);

    User findByLogin(String login);
}
