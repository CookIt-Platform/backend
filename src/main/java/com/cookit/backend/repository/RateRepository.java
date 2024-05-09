package com.cookit.backend.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cookit.backend.entity.Rate;
import com.cookit.backend.entity.RateId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RateRepository extends JpaRepository<Rate, RateId>{
    @Query(value = "SELECT AVG(value) FROM rate r WHERE r.post_id = :postID", nativeQuery = true)
    Double findPostAverageRating(@Param("postID") Long postID);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO rate(post_id, user_id, value) VALUES (:postID, :username, :value)", nativeQuery = true)
    void createRate(@Param("postID") Long postID, @Param("username") String username, @Param("value") Integer value);

    @Modifying
    @Transactional
    @Query(value = "UPDATE rate SET value = :value WHERE post_id = :postID AND user_id = :username", nativeQuery = true)
    void updateRate(@Param("value") Integer value, @Param("postID") Long postID, @Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM rate WHERE post_id = :postID AND user_id = :username", nativeQuery = true)
    void deleteRate(@Param("postID") Long postID, @Param("username") String username);

    @Query(value = "SELECT * FROM rate r WHERE r.post_id = :postID", nativeQuery = true)
    Set<Rate> getAllPostRates(@Param("postID") Long postID);

    @Query(value = "SELECT * FROM rate r WHERE r.user_id = :username", nativeQuery = true)
    Set<Rate> getAllUserRates(@Param("username") String username);
}
