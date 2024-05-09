package com.cookit.backend.service.impl;

import com.cookit.backend.dto.FollowDto;
import com.cookit.backend.repository.FollowsRepository;
import com.cookit.backend.service.FollowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FollowsServiceImpl implements FollowsService{
    private final FollowsRepository followsRepository;

    @Autowired
    public FollowsServiceImpl(FollowsRepository followsRepository) {
        this.followsRepository = followsRepository;
    }

    @Override
    public void createFollow(FollowDto followDto) {
        followsRepository.createFollower(followDto.getFollowee(), followDto.getFollower());
    }

    @Override
    public void deleteFollow(FollowDto followDto) {
        followsRepository.deleteFollower(followDto.getFollowee(), followDto.getFollower());
    }

    @Override
    public Long getNumFollowers(String username) {
        return followsRepository.getNumFollowers(username);
    }

    @Override
    public Long getNumFollowing(String username) {
        return followsRepository.getNumFollowing(username);
    }

    @Override
    public Set<String> getFollowers(String username) {
        return followsRepository.getFollowers(username);
    }

    @Override
    public Set<String> getFollowing(String username) {
        return followsRepository.getFollowing(username);
    }
}
