package com.cookit.backend.service.impl;

import com.cookit.backend.dto.BookmarkDto;
import com.cookit.backend.entity.*;
import com.cookit.backend.repository.BookmarkRepository;
import com.cookit.backend.repository.PostRepository;
import com.cookit.backend.repository.UserRepository;
import com.cookit.backend.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BookmarkServiceImpl implements BookmarkService{
    private final BookmarkRepository bookmarkRepository;

    @Autowired
    public BookmarkServiceImpl(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    @Override
    public void createBookmark(BookmarkDto bookmarkDto) {
        bookmarkRepository.addBookmark(bookmarkDto.getUsername(), bookmarkDto.getPostId());
    }

    @Override
    public void deleteBookmark(BookmarkDto bookmarkDto) {
        bookmarkRepository.removeBookmark(bookmarkDto.getUsername(), bookmarkDto.getPostId());
    }

    @Override
    public Set<Long> getAllPostsUserBookmarked(String username) {
        return bookmarkRepository.findAllPostsUserBookmarked(username);
    }

    @Override
    public Long getNumBookmarks(Long postId) {
        return bookmarkRepository.getNumOfBookmarks(postId);
    }


}
