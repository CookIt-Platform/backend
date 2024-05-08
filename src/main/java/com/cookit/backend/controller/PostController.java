package com.cookit.backend.controller;

import com.cookit.backend.entity.User;
import com.cookit.backend.response.PostResponse;
import com.cookit.backend.service.*;
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

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final UserLikesService userLikesService;
    private final CommentService commentService;
    private final BookmarkService bookmarkService;
    private final RateService rateService;
    private final PhotoService photoService;
    
    public PostController(PostService postService, UserLikesService userLikesService, CommentService commentService,
                          BookmarkService bookmarkService, RateService rateService, PhotoService photoService) {
        this.postService = postService;
        this.userLikesService = userLikesService;
        this.commentService = commentService;
        this.bookmarkService = bookmarkService;
        this.rateService = rateService;
        this.photoService = photoService;
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
        Post post = postService.getPost(id);
        if(post == null) {
            return ResponseEntity.badRequest().body("Post not found");
        }
        PostResponse postResponse = new PostResponse();
        postResponse.setId(post.getId());
        postResponse.setName(post.getName());
        postResponse.setPublishDate(post.getPublishDate());
        postResponse.setShortDescription(post.getShortDescription());
        postResponse.setSteps(post.getSteps());
        postResponse.setDifficulty(post.getDifficulty());
        postResponse.setTime(post.getTime());
        postResponse.setAuthor(post.getAuthor().getUsername());
        postResponse.setLikes(userLikesService.getAllLikes(post.getId()));
        postResponse.setComments(commentService.getAllComments(post.getId()));
        postResponse.setNumLikes(postResponse.getLikes().size());
        postResponse.setNumComments(postResponse.getComments().size());
        postResponse.setNumBookmarks(bookmarkService.getNumBookmarks(post.getId()));
        postResponse.setRates(rateService.getAllRates(post.getId()));
        postResponse.setAverageRating();
        postResponse.setPhotos(photoService.getAllPhotos(post.getId()));
        return ResponseEntity.ok(postResponse);
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }


}
