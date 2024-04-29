package com.cookit.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cookit.backend.entity.Comment;
import com.cookit.backend.entity.CommentId;

public interface CommentRepository extends JpaRepository<Comment, CommentId>{

}
