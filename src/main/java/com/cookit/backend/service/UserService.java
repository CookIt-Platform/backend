package com.cookit.backend.service;

import com.cookit.backend.entity.User;

import java.util.Map;

public interface UserService {
    public Map<String, String> createUser(User user);
}
