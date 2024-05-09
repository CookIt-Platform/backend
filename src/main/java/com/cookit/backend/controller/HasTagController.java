package com.cookit.backend.controller;

import com.cookit.backend.dto.HasTagDto;
import com.cookit.backend.service.HasTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/hastag")
public class HasTagController {
    private final HasTagService hasTagService;

    @Autowired
    public HasTagController(HasTagService hasTagService) {
        this.hasTagService = hasTagService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createHasTag(@RequestBody HasTagDto hasTagDto) {
        hasTagService.createHasTag(hasTagDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteHasTag(@RequestBody HasTagDto hasTagDto) {
        hasTagService.deleteHasTag(hasTagDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/posts/with/tag/{tagName}")
    public Set<Long> getPostsWithTag(@PathVariable String tagName) {
        return hasTagService.getPostsWithTag(tagName);
    }

    @GetMapping("/get/post/tags/{id}")
    public Set<String> getTagsOfPost(@PathVariable Long id) {
        return hasTagService.getTagsOfPost(id);
    }
}
