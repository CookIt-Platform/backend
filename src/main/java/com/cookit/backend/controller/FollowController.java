package com.cookit.backend.controller;

import com.cookit.backend.dto.FollowDto;
import com.cookit.backend.service.FollowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/follow")
public class FollowController {
    private final FollowsService followsService;

    @Autowired
    public FollowController(FollowsService followsService) {
        this.followsService = followsService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createFollow(@RequestBody FollowDto followDto) {
        followsService.createFollow(followDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFollow(@RequestBody FollowDto followDto) {
        followsService.deleteFollow(followDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/num/followers/{username}")
    public Long getNumFollowers(@PathVariable String username) {
        return followsService.getNumFollowers(username);
    }

    @GetMapping("/get/num/following/{username}")
    public Long getNumFollowing(@PathVariable String username) {
        return followsService.getNumFollowing(username);
    }

    @GetMapping("/get/followers/{username}")
    public Set<String> getFollowers(@PathVariable String username) {
        return followsService.getFollowers(username);
    }

    @GetMapping("/get/following/{username}")
    public Set<String> getFollowing(@PathVariable String username) {
        return followsService.getFollowing(username);
    }
}
