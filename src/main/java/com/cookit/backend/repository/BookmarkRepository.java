package com.cookit.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cookit.backend.entity.Bookmark;
import com.cookit.backend.entity.BookmarkId;

public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkId>{

}
