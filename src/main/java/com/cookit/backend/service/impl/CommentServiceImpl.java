package com.cookit.backend.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cookit.backend.dto.CommentDto;
import com.cookit.backend.entity.Comment;
import com.cookit.backend.entity.CommentId;
import com.cookit.backend.entity.Post;
import com.cookit.backend.entity.User;
import com.cookit.backend.repository.CommentRepository;
import com.cookit.backend.repository.PostRepository;
import com.cookit.backend.repository.UserRepository;
import com.cookit.backend.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void createComment(CommentDto commentDto) {
        // LocalDateTime date = LocalDateTime.now();
        commentRepository.createComment(commentDto.getDate(), commentDto.getUserId(), commentDto.getTextualContent(), commentDto.getPostId());
    }

    @Override
    public void deleteComment(CommentDto commentDto) {
        commentRepository.deleteComment(commentDto.getUserId(), commentDto.getTextualContent(), commentDto.getPostId());
    }

    @Override
    public void updateComment(CommentDto commentDto) {
    }

    @Override
    public void getComment(CommentId commentId) {
    }

    @Override
    public Long getNumComments(Long postId) {
        return commentRepository.getNumOfComments(postId);
    }

    @Override
    public Set<CommentDto> getUserComments(String username) {
        Set<Comment> comments = commentRepository.findAllUserComments(username);
        Set<CommentDto> commentDtos = new HashSet<>();
        for (Comment c : comments) {
            CommentDto commentDto = new CommentDto(c.getUserId().getUsername(), c.getPostId().getId(), c.getTextualContent(), c.getDate());
            commentDtos.add(commentDto);
        }
        return commentDtos;
    }

    @Override
    public Set<CommentDto> getPostComments(Long postId) {
        Set<Comment> comments = commentRepository.findAllPostComments(postId);
        Set<CommentDto> commentDtos = new HashSet<>();
        for (Comment c : comments) {
            CommentDto commentDto = new CommentDto(c.getUserId().getUsername(), c.getPostId().getId(), c.getTextualContent(), c.getDate());
            commentDtos.add(commentDto);
        }
        return commentDtos;
    }

}
