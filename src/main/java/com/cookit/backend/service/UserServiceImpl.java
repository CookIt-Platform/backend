package com.cookit.backend.service;

import com.cookit.backend.model.User;
import com.cookit.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public Map<String, String> createUser(User user) {
        // JSON response
        Map<String, String> response = new HashMap<>();

        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            response.put("error", "Username already exists");
            return response;
        }
        existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            response.put("error", "Email already exists");
            return response;
        }
        // get current date
        Date currDate = Date.valueOf(LocalDate.now());
        user.setJoinDate(currDate);
        userRepository.save(user);
        response.put("success", "User created successfully");
        return response;
    }
}
