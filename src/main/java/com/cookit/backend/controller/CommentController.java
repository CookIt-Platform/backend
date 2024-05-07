package com.cookit.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cookit.backend.dto.CommentDto;
import com.cookit.backend.entity.Comment;
import com.cookit.backend.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> createComment(@RequestBody CommentDto commentDto) {
        commentService.createComment(commentDto);
        return ResponseEntity.ok().build();    
    }

    @GetMapping("/get/all/post/{id}")
    public ResponseEntity<?> getAllComments(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getAllComments(id));
    }

    @GetMapping("/get/all/user/{username}")
    public ResponseEntity<?> getComment(@PathVariable String username) {
        return ResponseEntity.ok(commentService.getAllComments(username));
    }

}
