package com.cookit.backend.service;


import com.cookit.backend.dto.LikeDto;
import com.cookit.backend.entity.UserLikes;
import com.cookit.backend.entity.UserLikesId;

import java.util.Set;

public interface UserLikesService {
    public void createLike(LikeDto likeDto);
    public void deleteLike(LikeDto likeDto);
    public Set<Long> getAllLikes(String username);
    public Set<String> getPostLikes(Long postId);
    public Long getNumLikes(Long postId);
}
