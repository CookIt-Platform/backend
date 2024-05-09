package com.cookit.backend.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cookit.backend.entity.Follows;
import com.cookit.backend.entity.FollowsId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FollowsRepository extends JpaRepository<Follows, FollowsId>{
    @Query(value = "SELECT COUNT(*) from follows f WHERE f.followee = :username", nativeQuery = true)
    Long getNumFollowers(@Param("username")String username);

    @Query(value = "SELECT COUNT(*) from follows f WHERE f.follower = :username", nativeQuery = true)
    Long getNumFollowing(@Param("username")String username);

    @Query(value = "SELECT f.follower FROM follows f WHERE f.followee = :username", nativeQuery = true)
    Set<String> getFollowers(@Param("username") String username);

    @Query(value = "SELECT f.followee FROM follows f WHERE f.follower = :username", nativeQuery = true)
    Set<String> getFollowing(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO follows(followee, follower) VALUES (:followee, :follower)", nativeQuery = true)
    void createFollower(@Param("followee") String followee, @Param("follower") String follower);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM follows f WHERE f.followee = :followee AND f.follower = :follower", nativeQuery = true)
    void deleteFollower(@Param("followee") String followee, @Param("follower") String follower);
}
