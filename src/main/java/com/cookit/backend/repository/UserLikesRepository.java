package com.cookit.backend.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cookit.backend.entity.UserLikes;
import com.cookit.backend.entity.UserLikesId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface UserLikesRepository extends JpaRepository<UserLikes, UserLikesId>{
    @Query(value = "SELECT COUNT(*) FROM user_likes l WHERE l.post_id = :postID", nativeQuery = true)
    Long getNumLikes(@Param("postID") Long postID);

    @Query(value = "SELECT * FROM user_likes l WHERE l.user_id = :username", nativeQuery = true)
    Set<UserLikes> getUserLikes(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_likes(user_id, post_id) VALUES (:username, :postID)", nativeQuery = true)
    void createLike(@Param("username") String username, @Param("postID") Long postID);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_likes l WHERE l.user_id = :username AND l.post_id = :postID)", nativeQuery = true)
    void deleteLike(@Param("username") String username, @Param("postID") Long postID);

    @Query(value = "SELECT * FROM user_likes l WHERE l.post_id = :postID", nativeQuery = true)
    Set<UserLikes> getPostLikes(@Param("postID") Long postID);
}
