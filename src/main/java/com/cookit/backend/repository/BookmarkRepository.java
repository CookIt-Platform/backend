package com.cookit.backend.repository;

import com.cookit.backend.entity.Bookmark;
import com.cookit.backend.entity.BookmarkId;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkId> {
    @Query(value = "SELECT b.post_id from bookmark b WHERE b.user_id = :username", nativeQuery = true)
    Set<Long> findAllPostsUserBookmarked(@Param("username")String username);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO bookmark(user_id, post_id) VALUES (:username, :postID)", nativeQuery = true)
    void addBookmark(@Param("username") String username, @Param("postID") Long postID);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM bookmark b WHERE b.user_id = :username AND b.post_id = :postID", nativeQuery = true)
    void removeBookmark(@Param("username") String username, @Param("postID") Long postID);

    @Query(value = "SELECT COUNT(*) FROM bookmark b WHERE b.post_id = :postID", nativeQuery = true)
    Long getNumOfBookmarks(@Param("postID") Long postID);
}
