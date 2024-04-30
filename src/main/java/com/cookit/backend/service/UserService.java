package com.cookit.backend.service;

import com.cookit.backend.entity.User;
import com.cookit.backend.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    public boolean usernameExists(String username) {
        User existingUser = userRepository.findByUsernameCaseInsensitive(username);
        return existingUser != null;
    }

    @Transactional
    public void registerUser(User user) {
        if (!usernameExists(user.getUsername())) {
            LocalDate currDate = LocalDate.now();
            entityManager.createNativeQuery("INSERT INTO user (username, password, join_date) VALUES (?, ?, ?)")
                    .setParameter(1, user.getUsername())
                    .setParameter(2, user.getPassword())
                    .setParameter(3, currDate)
                    .executeUpdate();
        }
        else {
            throw new IllegalStateException("Username already exists");
        }
    }
}
