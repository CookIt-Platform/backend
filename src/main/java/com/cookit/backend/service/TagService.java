package com.cookit.backend.service;

import com.cookit.backend.dto.TagDto;

public interface TagService {
    public boolean createTag(TagDto tagDto);
    public boolean deleteTag(TagDto tagDto);
}
