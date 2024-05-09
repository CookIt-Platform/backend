package com.cookit.backend.controller;

import com.cookit.backend.dto.LikeDto;
import com.cookit.backend.entity.UserLikes;
import com.cookit.backend.entity.UserLikesId;
import com.cookit.backend.service.UserLikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/like")
public class LikeController {
    private final UserLikesService userLikesService;

    @Autowired
    public LikeController(UserLikesService userLikesService) {
        this.userLikesService = userLikesService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createLike(@RequestBody LikeDto likeDto) {
        userLikesService.createLike(likeDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteLike(@PathVariable LikeDto likeDto) {
        userLikesService.deleteLike(likeDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/all/post/{id}")
    public Set<String> getAllLikes(@PathVariable Long id) {
        return userLikesService.getPostLikes(id);
    }

    @GetMapping("/get/all/user/{username}")
    public Set<Long> getAllLikes(@PathVariable String username) {
        return userLikesService.getAllLikes(username);
    }
}
