package com.cookit.backend.service.impl;

import com.cookit.backend.dto.LikeDto;
import com.cookit.backend.entity.Post;
import com.cookit.backend.entity.User;
import com.cookit.backend.entity.UserLikes;
import com.cookit.backend.entity.UserLikesId;
import com.cookit.backend.repository.PostRepository;
import com.cookit.backend.repository.UserLikesRepository;
import com.cookit.backend.repository.UserRepository;
import com.cookit.backend.service.UserLikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserLikesServiceImpl implements UserLikesService{
    private UserLikesRepository userLikesRepository;

    @Autowired
    public UserLikesServiceImpl(UserLikesRepository userLikesRepository) {
        this.userLikesRepository = userLikesRepository;
    }
    @Override
    public void createLike(LikeDto likeDto) {
        userLikesRepository.createLike(likeDto.getUsername(), likeDto.getPostId());
    }

    @Override
    public void deleteLike(LikeDto likeDto) {
        userLikesRepository.deleteLike(likeDto.getUsername(), likeDto.getPostId());
    }

    @Override
    public Set<UserLikes> getAllLikes(String username) {
        return userLikesRepository.getUserLikes(username);
    }

    @Override
    public Set<UserLikes> getPostLikes(Long postId) {
        return userLikesRepository.getPostLikes(postId);
    }

    @Override
    public Long getNumLikes(Long postId) {
        return userLikesRepository.getNumLikes(postId);
    }
}
