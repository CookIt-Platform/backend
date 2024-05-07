package com.cookit.backend.service;

import com.cookit.backend.entity.Photo;

import java.util.Set;

public interface PhotoService {
    public Set<Photo> getAllPhotos(Long postId);
}
