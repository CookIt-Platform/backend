package com.cookit.backend.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cookit.backend.entity.Photo;
import com.cookit.backend.entity.PhotoId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, PhotoId>{
    @Query(value = "SELECT p.photo_url FROM photo p WHERE p.post_id = :postID", nativeQuery = true)
    Set<String> findAllPostPhotos(@Param("postID") Long postID);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO photo(photo_url, post_id) VALUES (:url, :postID)", nativeQuery = true)
    void createPhoto(@Param("url") String url, @Param("postID") Long postID);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM photo p WHERE p.photo_url = :url AND p.post_id = :postID", nativeQuery = true)
    void deletePhoto(@Param("url") String url, @Param("postID") Long postID);
}
