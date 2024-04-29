package com.cookit.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cookit.backend.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, String>{

}
