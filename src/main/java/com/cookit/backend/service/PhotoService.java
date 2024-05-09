package com.cookit.backend.service;

import com.cookit.backend.dto.PhotoDto;
import com.cookit.backend.entity.Photo;

import java.util.Set;

public interface PhotoService {
    public void createPhoto(PhotoDto photoDto);
    public void deletePhtoto(PhotoDto photoDto);
    public Set<String> getAllPhotos(Long postId);
}
