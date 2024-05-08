package com.cookit.backend.service;

import com.cookit.backend.dto.BookmarkDto;
import com.cookit.backend.entity.BookmarkId;

import java.util.Set;

public interface BookmarkService {
    public void createBookmark(BookmarkDto bookmarkDto);
    public void deleteBookmark(BookmarkDto bookmarkDto);
    public Set<Long> getAllPostsUserBookmarked(String username);
    public Long getNumBookmarks(Long postId);
}
