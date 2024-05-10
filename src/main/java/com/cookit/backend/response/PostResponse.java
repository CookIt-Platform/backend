package com.cookit.backend.response;

import com.cookit.backend.dto.CommentDto;
import com.cookit.backend.dto.IngredientDto;
import com.cookit.backend.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String name;
    private LocalDate publishDate;
    private String shortDescription;
    private String steps;
    private Difficulty difficulty;
    private Integer time;
    private String author;

    private Set<Rate> rates;
    private Set<String> photos;
    private Set<String> likes; // String of usernames
    private Set<CommentDto> comments; // Long of post id

    private Long numLikes;
    private Long numComments;
    private Long numBookmarks;
    private Double averageRating;

    private Set<String> hasTags;
    private Set<IngredientDto> containsIngredients;
}
