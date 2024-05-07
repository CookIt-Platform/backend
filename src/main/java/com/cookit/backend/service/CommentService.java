package com.cookit.backend.service;

import java.util.Set;

import com.cookit.backend.dto.CommentDto;
import com.cookit.backend.entity.CommentId;

public interface CommentService {

    public void createComment(CommentDto commentDto);
    public void deleteComment(CommentId commentId);
    public void updateComment(CommentDto commentDto);
    public void getComment(CommentId commentId);
    public Set<?> getAllComments(String username);
    public Set<?> getAllComments(Long postId);
}
