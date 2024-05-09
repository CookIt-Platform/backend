package com.cookit.backend.service;

import com.cookit.backend.controller.LoginRequest;
import com.cookit.backend.dto.SignUpDto;
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
    public void registerUser(SignUpDto signUpDto) {
        if (!usernameExists(signUpDto.getUsername())) {
            LocalDate currDate = LocalDate.now();
            entityManager.createNativeQuery("INSERT INTO user (username, bio, password, join_date, profile_picture) VALUES (?, ?, ?, ?, ?)")
                    .setParameter(1, signUpDto.getUsername())
                    .setParameter(2, signUpDto.getBio())
                    .setParameter(3, signUpDto.getPassword())
                    .setParameter(4, currDate)
                    .setParameter(5, signUpDto.getProfilePicture())
                    .executeUpdate();
        }
        else {
            throw new IllegalStateException("Username already exists");
        }
    }


    /*
     * This method is used to login a user. It checks if the username and password match and returns the user if they do.
     * @param loginRequest The login request containing the username and password.
     * @return The user if the username and password match, null otherwise.
     */
    public User loginUser(LoginRequest loginRequest) {
        User user = userRepository.findByUsernameCaseInsensitive(loginRequest.getUsername());
        if(user != null && user.getPassword().equals(loginRequest.getPassword())) {
            return user;
        }
        else {
            return null;
        }
    }

    public User getUser(String username) {
        return userRepository.findByUsernameCaseInsensitive(username);
    }
}
