package com.cookit.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cookit.backend.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

    @Query(value = "SELECT * FROM post p WHERE p.author = :username", nativeQuery = true)
    List<Post> getUserPosts(@Param("username") String username);

}
