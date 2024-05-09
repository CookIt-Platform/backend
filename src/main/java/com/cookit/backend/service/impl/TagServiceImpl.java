package com.cookit.backend.service.impl;

import com.cookit.backend.dto.TagDto;
import com.cookit.backend.entity.Tag;
import com.cookit.backend.repository.TagRepository;
import com.cookit.backend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService{
    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }


    @Override
    public boolean createTag(TagDto tagDto) {
        Tag tag = tagRepository.findByNameCaseInsensitive(tagDto.getTagName());
        if (tag == null) {
            tagRepository.createTag(tagDto.getTagName());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteTag(TagDto tagDto) {
        Tag tag = tagRepository.findByNameCaseInsensitive(tagDto.getTagName());
        if (tag != null) {
            tagRepository.deleteTag(tagDto.getTagName());
            return true;
        }
        return false;
    }
}
