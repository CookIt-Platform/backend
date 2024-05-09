package com.cookit.backend.repository;

import com.cookit.backend.dto.CommentDto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cookit.backend.entity.Comment;
import com.cookit.backend.entity.CommentId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface CommentRepository extends JpaRepository<Comment, CommentId> {
    @Query(value = "SELECT * FROM comment c WHERE c.post_id = :postID", nativeQuery = true)
    Set<Comment> findAllPostComments(@Param("postID") Long postID);

    @Query(value = "SELECT * FROM comment c WHERE c.user_id = :username", nativeQuery = true)
    Set<Comment> findAllUserComments(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO comment(date, user_id, textual_content, post_id) VALUES (:date, :username, :textualContent, :postID)", nativeQuery = true)
    void createComment(@Param("date") LocalDateTime date, @Param("username") String username, @Param("textualContent") String textualContent,
                        @Param("postID") Long postID);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM comment c WHERE c.user_id = :username AND c.textual_content = :textualContent AND c.post_id = :postID", nativeQuery = true)
    void deleteComment(@Param("username") String username, @Param("textualContent") String textualContent, @Param("postID") Long postID);

    @Modifying
    @Transactional
    @Query(value = "UPDATE comment SET textual_content = :newText WHERE user_id = :username AND " +
            "textual_content = :oldText AND post_id = :postID", nativeQuery = true)
    void updateComment(@Param("newText") String newText, @Param("username") String username, @Param("oldText") String oldText, @Param("postID") Long postID);

    @Query(value = "SELECT COUNT(*) FROM comment c WHERE c.post_id = :postID", nativeQuery = true)
    Long getNumOfComments(@Param("postID") Long postID);

    @Query(value = "SELECT * FROM comment c WHERE c.user_id = :username AND c.textual_content = :textContent " +
            "AND c.post_id = :postID", nativeQuery = true)
    CommentDto getComment(@Param("username") String username, @Param("textContent") String textContent, @Param("postID") Long postID);
}
