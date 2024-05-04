package com.cookit.backend.controller;

import com.cookit.backend.entity.User;
import com.cookit.backend.response.ErrorResponse;
import com.cookit.backend.response.UserResponse;
import com.cookit.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
            userService.registerUser(user);
            UserResponse userResponse = new UserResponse();
            userResponse.setUsername(user.getUsername());
            return ResponseEntity.ok(userResponse);
        } catch (IllegalStateException e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setError("UsernameAlreadyTaken");
            errorResponse.setMessage("Username '" + user.getUsername() + "' is already taken. Please choose a different username.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest user) {
        /*User userEntity = userService.loginUser(user);
        if (userEntity != null) {
            UserResponse userResponse = new UserResponse();
            userResponse.setUsername(user.getUsername());
            return ResponseEntity.ok(userEntity);
        }*/
        if (userService.loginUser(user) != null) {
            UserResponse userResponse = new UserResponse();
            userResponse.setUsername(user.getUsername());
            return ResponseEntity.ok(userResponse);
        }
        else {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setError("UsernameOrPasswordDoNotMatch");
            errorResponse.setMessage("Username or Password do not match or exist. Please try again.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

}
