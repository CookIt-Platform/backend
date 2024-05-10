package com.cookit.backend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cookit.backend.entity.Difficulty;
import com.cookit.backend.entity.Post;

import jakarta.transaction.Transactional;

public interface PostRepository extends JpaRepository<Post, Long>{

    @Query(value = "SELECT * FROM post p WHERE p.author = :username", nativeQuery = true)
    List<Post> getUserPosts(@Param("username") String username);

    @Query(value = "SELECT * FROM post p", nativeQuery = true)
    List<Post> getAllPosts();

    @Query(value = "SELECT * FROM post p WHERE p.difficulty = :difficulty", nativeQuery = true)
    List<Post> getPostsByDifficulty(@Param("difficulty") String difficulty);

    @Query(value = "SELECT * FROM post p WHERE p.difficulty = :difficulty and p.author = :username", nativeQuery = true)
    List<Post> getPostsByDifficultyAndUser(@Param("difficulty") String difficulty, @Param("username") String username);

    @Query(value = "SELECT * FROM post p WHERE p.id =:id", nativeQuery = true)
    Post getPost(Long id);

    @Query(value = "SELECT * FROM post p ORDER BY p.id DESC LIMIT 1", nativeQuery = true)
    Post getLastPost();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO post(difficulty, name, publish_date, short_description, steps, time, author)"
                    + "VALUES(:difficulty, :name, :date, :description, :steps, :time, :author)", nativeQuery = true)
    void createPost(@Param("difficulty") String difficulty, @Param("name") String name, @Param("date") LocalDate date,
                    @Param("description") String description, @Param("steps") String steps, @Param("time") Integer time,
                    @Param("author") String author);

    @Query(value = "SELECT * FROM post p JOIN ( SELECT u.post_id, COUNT(*) as likes FROM user_likes u GROUP BY u.post_id ORDER BY likes DESC LIMIT :num) t ON p.id = t.post_id ORDER BY t.likes DESC", nativeQuery = true)
    List<Post> getTopLikedPosts(@Param("num") Integer num);

    @Query(value = "SELECT * FROM post p ORDER BY p.publish_date DESC LIMIT :num", nativeQuery = true)
    List<Post> getRecentPosts(@Param("num") Integer num);

    @Query(value = "SELECT * FROM post p WHERE p.name LIKE CONCAT('%', :keyword, '%')", nativeQuery = true)
    List<Post> findPostsByKeyword(@Param("keyword") String keyword);

}
