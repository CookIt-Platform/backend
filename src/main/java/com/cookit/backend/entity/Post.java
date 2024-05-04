

package com.cookit.backend.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 256)
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "publish_date", nullable = false)
    private LocalDate publishDate;

    @Column(name = "short_description", length = 256)
    private String shortDescription;

    @Column(name = "steps", length = 1000)
    private String steps;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty", 
            columnDefinition = "ENUM('easy', 'medium', 'hard')", 
            nullable = false)
    private Difficulty difficulty;

    @Column(name = "time", nullable = false)
    private Integer time;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "author", referencedColumnName = "username", nullable = false, foreignKey = @ForeignKey(name = "FK_author_post"))
    private User author;

    @OneToMany(mappedBy = "postId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonBackReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Photo> photos = new HashSet<>();

    @OneToMany(mappedBy = "postId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonBackReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Rate> rates = new HashSet<>();

    @OneToMany(mappedBy = "postId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonBackReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<UserLikes> likes = new HashSet<>();

    @OneToMany(mappedBy = "postId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonBackReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Bookmark> bookmarks = new HashSet<>();
    
    @OneToMany(mappedBy = "postId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonBackReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "postId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonBackReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<HasTag> hasTags = new HashSet<>();

    @OneToMany(mappedBy = "postId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonBackReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<ContainsIngredient> containsIngredients = new HashSet<>();
    
}
