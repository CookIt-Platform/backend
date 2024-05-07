package com.cookit.backend.service.impl;

import com.cookit.backend.entity.Photo;
import com.cookit.backend.repository.PhotoRepository;
import com.cookit.backend.repository.PostRepository;
import com.cookit.backend.service.PhotoService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PhotoServiceImpl implements PhotoService{
    private final PhotoRepository photoRepository;
    private final PostRepository postRepository;

    public PhotoServiceImpl(PhotoRepository photoRepository, PostRepository postRepository) {
        this.photoRepository = photoRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Set<Photo> getAllPhotos(Long postId) {
        return postRepository.findById(postId).orElseThrow().getPhotos();
    }
}
