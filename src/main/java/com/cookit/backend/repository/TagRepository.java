package com.cookit.backend.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.cookit.backend.entity.Tag;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, String>{
    @Query(value = "SELECT * FROM tag t WHERE LOWER(t.tag_name) = LOWER(:name)", nativeQuery = true)
    Tag findByNameCaseInsensitive(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tag(tag_name) VALUES (:name)", nativeQuery = true)
    void createTag(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tag WHERE tag_name = :name", nativeQuery = true)
    void deleteTag(@Param("name") String name);
}
