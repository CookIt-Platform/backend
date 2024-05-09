package com.cookit.backend.response;

import com.cookit.backend.dto.CommentDto;
import com.cookit.backend.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String username;
    private LocalDate joinDate;
    private String bio;
    private String profilePicture;
    private Set<String> followers;
    private Set<String> following;
    private Long numFollowers;
    private Long numFollowing;
    private Set<Rate> rates;
    private Set<Post> posts;
    private Set<Long> likes;
    private Set<Long> bookmarks;
    private Set<CommentDto> comments;
}
