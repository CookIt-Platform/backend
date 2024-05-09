package com.cookit.backend.controller;

import com.cookit.backend.entity.Rate;
import com.cookit.backend.entity.User;
import com.cookit.backend.response.ErrorResponse;
import com.cookit.backend.response.UserResponse;
import com.cookit.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final FollowsService followsService;
    private final UserLikesService userLikesService;
    private final BookmarkService bookmarkService;
    private final CommentService commentService;
    private final RateService rateService;
    private final PostService postService;

    @Autowired
    public UserController(UserService userService, FollowsService followsService, UserLikesService userLikesService,
                          BookmarkService bookmarkService, CommentService commentService, RateService rateService,
                          PostService postService) {
        this.userService = userService;
        this.followsService = followsService;
        this.userLikesService = userLikesService;
        this.bookmarkService = bookmarkService;
        this.commentService = commentService;
        this.rateService = rateService;
        this.postService = postService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
            userService.registerUser(user);
            User signedUpUser = userService.getUser(user.getUsername());
            return ResponseEntity.ok(createResponse(signedUpUser));
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
            User loggedInUser = userService.getUser(user.getUsername());
            return ResponseEntity.ok(createResponse(loggedInUser));
        }
        else {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setError("UsernameOrPasswordDoNotMatch");
            errorResponse.setMessage("Username or Password do not match or exist. Please try again.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        User user = userService.getUser(username);
        if(user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.ok(createResponse(user));
    }

    public UserResponse createResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(user.getUsername());
        userResponse.setJoinDate(user.getJoinDate());
        userResponse.setBio(user.getBio());
        userResponse.setProfilePicture(user.getProfilePicture());
        userResponse.setNumFollowers(followsService.getNumFollowers(user.getUsername()));
        userResponse.setNumFollowing(followsService.getNumFollowing(user.getUsername()));
        userResponse.setNumFollowers(followsService.getNumFollowers(user.getUsername()));
        userResponse.setNumFollowing(followsService.getNumFollowing(user.getUsername()));
        userResponse.setRates(rateService.getAllUserRates(user.getUsername()));
        userResponse.setLikes(userLikesService.getAllLikes(user.getUsername()));
        userResponse.setBookmarks(bookmarkService.getAllPostsUserBookmarked(user.getUsername()));
        userResponse.setComments(commentService.getAllComments(user.getUsername()));
        return userResponse;
    }
}
