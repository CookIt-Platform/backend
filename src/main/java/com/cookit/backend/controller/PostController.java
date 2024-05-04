package com.cookit.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cookit.backend.dto.PostDto;
import com.cookit.backend.entity.Post;
import com.cookit.backend.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    
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
        return ResponseEntity.ok("Post deleted successfully");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id) {
        Post post = postService.getPost(id);
        if(post == null) {
            return ResponseEntity.badRequest().body("Post not found");
        }
        return ResponseEntity.ok(post);
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }


}
