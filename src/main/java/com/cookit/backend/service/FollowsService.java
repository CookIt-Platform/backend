package com.cookit.backend.service;

import com.cookit.backend.dto.FollowDto;

import java.util.Set;

public interface FollowsService {
    public void createFollow(FollowDto followDto);
    public void deleteFollow(FollowDto followDto);
    public Long getNumFollowers(String username);
    public Long getNumFollowing(String username);
    public Set<String> getFollowers(String username);
    public Set<String> getFollowing(String username);
}
