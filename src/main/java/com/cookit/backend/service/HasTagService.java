package com.cookit.backend.service;

import com.cookit.backend.dto.HasTagDto;
import com.cookit.backend.entity.Post;
import com.cookit.backend.entity.Tag;

import java.util.Set;

public interface HasTagService {
    Set<Long> getPostsWithTag(String tagName);
    Set<String> getTagsOfPost(Long postID);
    void createHasTag(HasTagDto hasTagDto);
    void deleteHasTag(HasTagDto hasTagDto);
}
