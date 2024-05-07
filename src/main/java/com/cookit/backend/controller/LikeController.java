package com.cookit.backend.controller;

import com.cookit.backend.dto.LikeDto;
import com.cookit.backend.service.UserLikesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikeController {
    private final UserLikesService userLikesService;

    public LikeController(UserLikesService userLikesService) {
        this.userLikesService = userLikesService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createLike(@RequestBody LikeDto likeDto) {
        userLikesService.createLike(likeDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/all/post/{id}")
    public ResponseEntity<?> getAllLikes(@PathVariable Long id) {
        return ResponseEntity.ok(userLikesService.getAllLikes(id));
    }

    @GetMapping("/get/all/user/{username}")
    public ResponseEntity<?> getAllLikes(@PathVariable String username) {
        return ResponseEntity.ok(userLikesService.getAllLikes(username));
    }
}
