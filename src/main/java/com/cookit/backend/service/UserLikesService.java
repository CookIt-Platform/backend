package com.cookit.backend.service;


import com.cookit.backend.dto.LikeDto;
import com.cookit.backend.entity.UserLikesId;

import java.util.Set;

public interface UserLikesService {
    public void createLike(LikeDto likeDto);
    public void deleteLike(UserLikesId userLikesId);
    public Set<?> getAllLikes(String username);
    public Set<?> getAllLikes(Long postId);
}
