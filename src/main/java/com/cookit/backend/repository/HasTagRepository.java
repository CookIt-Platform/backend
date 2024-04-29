package com.cookit.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cookit.backend.entity.HasTag;
import com.cookit.backend.entity.HasTagId;

public interface HasTagRepository extends JpaRepository<HasTag, HasTagId>{

}
