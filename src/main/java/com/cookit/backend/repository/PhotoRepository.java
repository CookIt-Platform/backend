package com.cookit.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cookit.backend.entity.Photo;
import com.cookit.backend.entity.PhotoId;

public interface PhotoRepository extends JpaRepository<Photo, PhotoId>{

}
