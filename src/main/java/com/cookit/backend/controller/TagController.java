package com.cookit.backend.controller;

import com.cookit.backend.dto.TagDto;
import com.cookit.backend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
public class TagController {
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTag(@RequestBody TagDto tagDto) {
        if (tagService.createTag(tagDto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTag(@RequestBody TagDto tagDto) {
        if (tagService.deleteTag(tagDto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
