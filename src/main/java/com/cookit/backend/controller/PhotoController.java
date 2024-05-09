package com.cookit.backend.controller;

import com.cookit.backend.dto.PhotoDto;
import com.cookit.backend.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/photo")
public class PhotoController {
    private final PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPhoto(@RequestBody PhotoDto photoDto) {
        photoService.createPhoto(photoDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deletePhoto(@RequestBody PhotoDto photoDto) {
        photoService.deletePhtoto(photoDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/all/{id}")
    public Set<String> getAllPhotos(@PathVariable Long id) {
        return photoService.getAllPhotos(id);
    }
}
