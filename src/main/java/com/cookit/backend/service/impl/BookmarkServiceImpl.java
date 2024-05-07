package com.cookit.backend.service.impl;

import com.cookit.backend.dto.BookmarkDto;
import com.cookit.backend.entity.*;
import com.cookit.backend.repository.BookmarkRepository;
import com.cookit.backend.repository.PostRepository;
import com.cookit.backend.repository.UserRepository;
import com.cookit.backend.service.BookmarkService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BookmarkServiceImpl implements BookmarkService{
    private BookmarkRepository bookmarkRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;

    public BookmarkServiceImpl(BookmarkRepository bookmarkRepository, PostRepository postRepository, UserRepository userRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createBookmark(BookmarkDto bookmarkDto) {
        Bookmark bookmark = new Bookmark();
        User user = userRepository.findById(bookmarkDto.getUsername()).orElseThrow();
        Post post = postRepository.findById(bookmarkDto.getPostId()).orElseThrow();
        bookmark.setUserId(user);
        bookmark.setPostId(post);
        bookmarkRepository.save(bookmark);
    }

    @Override
    public void deleteBookmark(BookmarkId bookmarkId) {
        bookmarkRepository.deleteById(bookmarkId);
    }

    @Override
    public void getBookmark(BookmarkId bookmarkId) {

    }

    @Override
    public Set<?> getAllBookmarks(String username) {
        return userRepository.findById(username).orElseThrow().getBookmarks();
    }
}
