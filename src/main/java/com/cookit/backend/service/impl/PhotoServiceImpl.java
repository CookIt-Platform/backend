package com.cookit.backend.service.impl;

import com.cookit.backend.dto.PhotoDto;
import com.cookit.backend.entity.Photo;
import com.cookit.backend.repository.PhotoRepository;
import com.cookit.backend.repository.PostRepository;
import com.cookit.backend.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PhotoServiceImpl implements PhotoService{
    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public void createPhoto(PhotoDto photoDto) {
        photoRepository.createPhoto(photoDto.getUrl(), photoDto.getPostId());
    }

    @Override
    public void deletePhtoto(PhotoDto photoDto) {
        photoRepository.deletePhoto(photoDto.getUrl(), photoDto.getPostId());
    }

    @Override
    public Set<String> getAllPhotos(Long postId) {
        return photoRepository.findAllPostPhotos(postId);
    }
}
