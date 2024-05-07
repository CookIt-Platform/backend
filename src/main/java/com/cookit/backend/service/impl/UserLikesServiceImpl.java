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
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserLikesServiceImpl implements UserLikesService{
    private UserLikesRepository userLikesRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;

    public UserLikesServiceImpl(UserLikesRepository userLikesRepository, PostRepository postRepository, UserRepository userRepository) {
        this.userLikesRepository = userLikesRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }
    @Override
    public void createLike(LikeDto likeDto) {
        UserLikes userLikes = new UserLikes();
        User user = userRepository.findById(likeDto.getUsername()).orElseThrow();
        Post post = postRepository.findById(likeDto.getPostId()).orElseThrow();
        userLikes.setUserId(user);
        userLikes.setPostId(post);
        userLikesRepository.save(userLikes);
    }

    @Override
    public void deleteLike(UserLikesId userLikesId) {
        userLikesRepository.deleteById(userLikesId);
    }

    @Override
    public Set<?> getAllLikes(String username) {
        return userRepository.findById(username).orElseThrow().getUserLikes();
    }

    @Override
    public Set<?> getAllLikes(Long postId) {
        return postRepository.findById(postId).orElseThrow().getLikes();
    }
}
