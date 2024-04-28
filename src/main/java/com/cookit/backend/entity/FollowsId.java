package com.cookit.backend.entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FollowsId implements Serializable {
    private String follower;
    private String followee;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowsId that = (FollowsId) o;
        return Objects.equals(follower, that.follower) &&
                Objects.equals(followee, that.followee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(follower, followee);
    }
}
