package com.cookit.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.ForeignKey;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "follows")
@IdClass(FollowsId.class)
public class Follows {
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "follower", referencedColumnName = "username", foreignKey = @ForeignKey(name = "FK_follower"))
    private User follower;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "followee", referencedColumnName = "username", foreignKey = @ForeignKey(name = "FK_followee"))
    private User followee;
}
