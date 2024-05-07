package com.cookit.backend.service;

import com.cookit.backend.dto.BookmarkDto;
import com.cookit.backend.entity.BookmarkId;

import java.util.Set;

public interface BookmarkService {
    public void createBookmark(BookmarkDto bookmarkDto);
    public void deleteBookmark(BookmarkId bookmarkId);
    public void getBookmark(BookmarkId bookmarkId);
    public Set<?> getAllBookmarks(String username);
    public Set<?> getAllBookmarks(Long postId);
}
