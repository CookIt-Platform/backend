package com.cookit.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cookit.backend.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

    @Query(value = "SELECT * FROM post p WHERE p.author = :username", nativeQuery = true)
    List<Post> getUserPosts(@Param("username") String username);

    @Query(value = "SELECT * FROM post p WHERE p.difficulty = :difficulty", nativeQuery = true)
    List<Post> getPostsByDifficulty(@Param("difficulty") String difficulty);

    @Query(value = "SELECT * FROM post p WHERE p.difficulty = :difficulty and p.author = :username", nativeQuery = true)
    List<Post> getPostsByDifficultyAndUser(@Param("difficulty") String difficulty, @Param("username") String username);

}
