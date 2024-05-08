package com.cookit.backend.controller;

import com.cookit.backend.dto.BookmarkDto;
import com.cookit.backend.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookmark")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @Autowired
    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBookmark(@RequestBody BookmarkDto bookmarkDto) {
        bookmarkService.createBookmark(bookmarkDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteBookmark(@RequestBody BookmarkDto bookmarkDto) {
        bookmarkService.deleteBookmark(bookmarkDto);
        return ResponseEntity.ok().build();
    }
}
