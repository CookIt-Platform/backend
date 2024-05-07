package com.cookit.backend.service.impl;

import java.util.Set;

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

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setTextualContent(commentDto.getTextualContent());
        comment.setDate(commentDto.getDate());
        User user = userRepository.findById(commentDto.getUserId()).orElseThrow();
        Post post = postRepository.findById(commentDto.getPostId()).orElseThrow();
        comment.setUserId(user);
        comment.setPostId(post);
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(CommentId commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public void updateComment(CommentDto commentDto) {
    }

    @Override
    public void getComment(CommentId commentId) {
    }

    @Override
    public Set<?> getAllComments(String username) {
        return userRepository.findById(username).orElseThrow().getComments();
    }

    @Override
    public Set<?> getAllComments(Long postId) {
        return postRepository.findById(postId).orElseThrow().getComments();
    }

}
