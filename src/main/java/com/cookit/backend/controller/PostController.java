package com.cookit.backend.controller;

import com.cookit.backend.entity.User;
import com.cookit.backend.response.PostResponse;
import com.cookit.backend.service.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cookit.backend.dto.PostDto;
import com.cookit.backend.entity.Post;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody PostDto postDto) {
        Post savedPost = postService.createPost(postDto);
        if(savedPost == null) {
            return ResponseEntity.badRequest().body("Post creation failed. Please try again.");
        }
        return ResponseEntity.ok(savedPost);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updatePost(@RequestBody Post post) {
        postService.updatePost(post);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id) {
        PostResponse post = postService.getPost(id);
        if(post == null) {
            return ResponseEntity.badRequest().body("Post not found");
        }
        return ResponseEntity.ok(post);
    }

    @GetMapping("/get/top/{num}")
    public ResponseEntity<?> getTopLikedPosts(@PathVariable Integer num) {
        List<PostResponse> posts = postService.getTopLikedPosts(num);
        if(posts == null || posts.isEmpty()) {
            return ResponseEntity.badRequest().body("No posts found");
        }
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/get/recent/{num}")
    public ResponseEntity<?> getRecentPosts(@PathVariable Integer num) {
        List<PostResponse> posts = postService.getRecentPosts(num);
        if(posts == null || posts.isEmpty()) {
            return ResponseEntity.badRequest().body("No posts found");
        }
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getPosts(@RequestParam(required = false) String username, @RequestParam(required = false) String difficulty) {
        List<PostResponse> posts;

        if (username != null && difficulty != null) {
            posts = postService.getPostsByUserAndDifficulty(username, difficulty);
        } else if (username != null) {
            posts = postService.getPostsByUser(username);
        } else if (difficulty != null) {
            posts = postService.getPostsByDifficulty(difficulty);
        } else {
            posts = postService.getAllPosts();
        }

        if(posts == null || posts.isEmpty()) {
            return ResponseEntity.badRequest().body("No posts found for the given criteria");
        }

        return ResponseEntity.ok(posts);
    }

    @GetMapping("/search/posts/{keyword}")
    public ResponseEntity<?> searchPosts(@PathVariable String keyword) {
        List<PostResponse> posts;
        posts = postService.findPostsByKeyword(keyword);
        return ResponseEntity.ok(posts);
    }

}
