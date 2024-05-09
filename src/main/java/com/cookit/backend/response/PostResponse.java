package com.cookit.backend.response;

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
    private Set<UserLikes> likes;
    private Set<Comment> comments;

    private Long numLikes;
    private Long numComments;
    private Long numBookmarks;
    private Double averageRating;

    private Set<String> hasTags;
    private Set<ContainsIngredient> containsIngredients;
}
