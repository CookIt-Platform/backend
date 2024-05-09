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
    private final UserLikesService userLikesService;
    private final CommentService commentService;
    private final BookmarkService bookmarkService;
    private final RateService rateService;
    private final PhotoService photoService;
    private final HasTagService hasTagService;

    @Autowired
    public PostController(PostService postService, UserLikesService userLikesService, CommentService commentService,
                          BookmarkService bookmarkService, RateService rateService, PhotoService photoService,
                          HasTagService hasTagService) {
        this.postService = postService;
        this.userLikesService = userLikesService;
        this.commentService = commentService;
        this.bookmarkService = bookmarkService;
        this.rateService = rateService;
        this.photoService = photoService;
        this.hasTagService = hasTagService;
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
        return ResponseEntity.ok(createResponse(post));
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getPosts(@RequestParam(required = false) String username, @RequestParam(required = false) String difficulty) {
        List<Post> posts;

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

        PostResponse[] postResponses = new PostResponse[posts.size()];
        for(int i = 0; i < posts.size(); i++) {
            postResponses[i] = createResponse(posts.get(i));
        }
        return ResponseEntity.ok(postResponses);
    }


    public PostResponse createResponse(Post post) {
        PostResponse postResponse = new PostResponse();
        postResponse.setId(post.getId());
        postResponse.setName(post.getName());
        postResponse.setPublishDate(post.getPublishDate());
        postResponse.setShortDescription(post.getShortDescription());
        postResponse.setSteps(post.getSteps());
        postResponse.setDifficulty(post.getDifficulty());
        postResponse.setTime(post.getTime());
        postResponse.setAuthor(post.getAuthor().getUsername());
        postResponse.setLikes(userLikesService.getPostLikes(post.getId()));
        postResponse.setComments(commentService.getPostComments(post.getId()));
        postResponse.setNumLikes(userLikesService.getNumLikes(post.getId()));
        postResponse.setNumComments(commentService.getNumComments(post.getId()));
        postResponse.setNumBookmarks(bookmarkService.getNumBookmarks(post.getId()));
        postResponse.setRates(rateService.getAllPostRates(post.getId()));
        postResponse.setAverageRating(rateService.getAverageRating(post.getId()));
        postResponse.setPhotos(photoService.getAllPhotos(post.getId()));
        postResponse.setHasTags(hasTagService.getTagsOfPost(post.getId()));
        return postResponse;
    }
}
