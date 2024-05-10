package com.cookit.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import com.cookit.backend.entity.Difficulty;
import com.cookit.backend.entity.Post;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Long id;
    private String name;
    private LocalDate publishDate;
    private String shortDescription;
    private String steps;
    private String difficulty;
    private Integer time;
    private String author;
    private String[] tags;
    private IngredientDto[] ingredients;
    private String[] photos;

}

