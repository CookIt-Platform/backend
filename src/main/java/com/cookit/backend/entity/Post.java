

package com.cookit.backend.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", length = 256)
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "publish_date", nullable = false)
    private LocalDate publishDate;

    @Column(name = "short_description", length = 256)
    private String shortDescription;

    @Column(name = "steps", length = 1000)
    private String steps;
/*
    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty", length = 5, 
            columnDefinition = "ENUM('easy', 'medium', 'hard')", 
            nullable = false)
    private String difficulty;
*/
    @Column(name = "difficulty", length = 5, nullable = false)
    private String difficulty;

    @Column(name = "time", nullable = false)
    private int time;
/* 
    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "username", nullable = false, 
                foreignKey = @ForeignKey(name = "FK_post_author"))
    private User author;

    FOREIGN KEY(author) REFERENCES User(username) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT difficulty_range CHECK (difficulty = 'easy' OR difficulty = 'medium' OR difficulty = 'hard')
*/
}
