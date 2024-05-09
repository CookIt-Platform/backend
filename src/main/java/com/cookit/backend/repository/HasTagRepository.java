package com.cookit.backend.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cookit.backend.entity.HasTag;
import com.cookit.backend.entity.HasTagId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface HasTagRepository extends JpaRepository<HasTag, HasTagId>{
    // find posts with a specific tag
    @Query(value = "SELECT DISTINCT t.post_id FROM has_tag t WHERE LOWER(t.tag_name) = LOWER(:name)", nativeQuery = true)
    Set<Long> getPostsWithTag(@Param("name") String name);

    // find the tags of a post
    @Query(value = "SELECT tag_name FROM has_tag WHERE post_id = :postID", nativeQuery = true)
    Set<String> getTagsOfPost(@Param("postID") Long postID);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO has_tag(post_id, tag_name) VALUES(:postID, :tag)", nativeQuery = true)
    void createHasTag(@Param("postID") Long postID, @Param("tag") String tag);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM has_tag WHERE post_id = :postID AND tag_name = :tag", nativeQuery = true)
    void deleteHasTag(@Param("postID") Long postID, @Param("tag") String tag);
}
