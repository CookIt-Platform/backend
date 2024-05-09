package com.cookit.backend.service;

import java.util.Set;

import com.cookit.backend.dto.CommentDto;
import com.cookit.backend.entity.Comment;
import com.cookit.backend.entity.CommentId;

public interface CommentService {

    public void createComment(CommentDto commentDto);
    public void deleteComment(CommentDto commentDto);
    public void updateComment(CommentDto commentDto);
    public void getComment(CommentId commentId);
    public Set<CommentDto> getUserComments(String username);
    public Set<CommentDto> getPostComments(Long postId);
    public Long getNumComments(Long postId);
}
