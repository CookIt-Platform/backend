package com.cookit.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.cookit.backend.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, String>{

     @Query("SELECT i FROM Tag i WHERE LOWER(i.tagName) = LOWER(:name)")
    Tag findByNameCaseInsensitive(@Param("name") String name);
}
