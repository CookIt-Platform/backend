package com.cookit.backend.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
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
@Table(name = "user")
public class User {
    @Id
    @Column(name = "username", length = 256)
    private String username;

    @Column(name = "password", nullable = false, length = 256)
    private String password;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "join_date", nullable = false, columnDefinition = "DATE")
    private LocalDate joinDate;

    @Column(name = "bio", length = 256)
    private String bio;

    @Column(name = "profile_picture", length = 256)
    private String profilePicture;

    @OneToMany(mappedBy = "follower", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Follows> followers = new HashSet<>();

    @OneToMany(mappedBy = "followee", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Follows> followees = new HashSet<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Post> posts = new HashSet<>();

    @OneToMany(mappedBy = "userId", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Rate> rates = new HashSet<>();

    @OneToMany(mappedBy = "userId", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<UserLikes> userLikes = new HashSet<>();

    @OneToMany(mappedBy = "userId", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Bookmark> bookmarks = new HashSet<>();

    @OneToMany(mappedBy = "userId", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();
}

