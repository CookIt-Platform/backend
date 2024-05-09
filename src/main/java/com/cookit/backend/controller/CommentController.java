package com.cookit.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cookit.backend.dto.CommentDto;
import com.cookit.backend.entity.Comment;
import com.cookit.backend.service.CommentService;

import java.util.Set;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> createComment(@RequestBody CommentDto commentDto) {
        commentService.createComment(commentDto);
        return ResponseEntity.ok().build();    
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteComment(@RequestBody CommentDto commentDto) {
        commentService.deleteComment(commentDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/all/post/{id}")
    public Set<Comment> getPostComments(@PathVariable Long id) {
        return commentService.getPostComments(id);
    }

    @GetMapping("/get/all/user/{username}")
    public Set<Comment> getComment(@PathVariable String username) {
        return commentService.getUserComments(username);
    }

    @GetMapping("/get/num/post/comments/{id}")
    public Long getNumComments(@PathVariable Long id) {
        return commentService.getNumComments(id);
    }

}
