package com.cookit.backend.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @Column(name = "tag_name", length = 256, nullable = false)
    private String tagName;

    @OneToMany(mappedBy = "tagName", fetch = FetchType.LAZY)
    private Set<HasTag> hasTags;
}
