package com.cookit.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "follows")
//@IdClass(FollowsId.class)
public class Follows {
    @Id
    @ManyToOne
    @Column(name = "follower")
    @JoinColumn(name = "follower", referencedColumnName = "username")
    private User follower;

    @Id
    @ManyToOne
    @Column(name = "followee")
    @JoinColumn(name = "followee", referencedColumnName = "username")
    private User followee;
}

/*
 * import java.io.Serializable;
 * @Getter
 * @Setter
 * @NoArgsConstructor
 * @AllArgsConstructor
 * public class FollowsId implements Serializable {
    private String follower;
    private String followee;

    // getters, setters

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
 */
