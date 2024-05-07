package com.cookit.backend.controller;

import com.cookit.backend.dto.BookmarkDto;
import com.cookit.backend.service.BookmarkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookmark")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createComment(@RequestBody BookmarkDto bookmarkDto) {
        bookmarkService.createBookmark(bookmarkDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/all/user/{username}")
    public ResponseEntity<?> getAllBookmarks(@PathVariable String username) {
        return ResponseEntity.ok(bookmarkService.getAllBookmarks(username));
    }
}
