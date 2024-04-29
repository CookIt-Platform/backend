package com.cookit.backend.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentId implements Serializable {
    private String userId;
    private Long postId;
    private String textualContent;

}
