package com.cookit.backend.service.impl;

import com.cookit.backend.dto.HasTagDto;
import com.cookit.backend.entity.Post;
import com.cookit.backend.entity.Tag;
import com.cookit.backend.repository.HasTagRepository;
import com.cookit.backend.repository.TagRepository;
import com.cookit.backend.service.HasTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class HasTagServiceImpl implements HasTagService{
    private final HasTagRepository hasTagRepository;
    private final TagRepository tagRepository;

    @Autowired
    public HasTagServiceImpl(HasTagRepository hasTagRepository, TagRepository tagRepository) {
        this.hasTagRepository = hasTagRepository;
        this.tagRepository = tagRepository;
    }


    @Override
    public Set<Long> getPostsWithTag(String tagName) {
        return hasTagRepository.getPostsWithTag(tagName);
    }

    @Override
    public Set<String> getTagsOfPost(Long postID) {
        return hasTagRepository.getTagsOfPost(postID);
    }

    @Override
    public void createHasTag(HasTagDto hasTagDto) {
        // create the tag if the tag does not exist
        if (tagRepository.findByNameCaseInsensitive(hasTagDto.getTagName()) == null) {
            tagRepository.createTag(hasTagDto.getTagName());
        }
        hasTagRepository.createHasTag(hasTagDto.getPostId(), hasTagDto.getTagName());
    }

    @Override
    public void deleteHasTag(HasTagDto hasTagDto) {
        hasTagRepository.deleteHasTag(hasTagDto.getPostId(), hasTagDto.getTagName());
    }
}
