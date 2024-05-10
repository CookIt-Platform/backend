package com.cookit.backend.repository;

import com.cookit.backend.entity.MeasurementUnit;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cookit.backend.entity.ContainsIngredient;
import com.cookit.backend.entity.ContainsIngredientId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ContainsIngredientRepository extends JpaRepository<ContainsIngredient, ContainsIngredientId>{
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO contains_ingredient(ingredient_name, post_id, quantity, unit) VALUES(:name, :postID, :qty, :unit)", nativeQuery = true)
    void createContainsIngredient(@Param("name") String name, @Param("postID") Long postID, @Param("qty") Double qty, @Param("unit") String unit);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM contains_ingredient WHERE LOWER(ingredient_name) = LOWER(:name) AND post_id = :postID", nativeQuery = true)
    void deleteContainsIngredient(@Param("name") String name, @Param("postID") Long postID);

    @Modifying
    @Transactional
    @Query(value = "UPDATE contains_ingredient SET quantity = :qty, unit = :unit WHERE LOWER(ingredient_name) = LOWER(:name) AND post_id = :postID", nativeQuery = true)
    void updateContainsIngredient(@Param("qty") Double qty, @Param("unit")MeasurementUnit unit, @Param("name") String name, @Param("postID") Long postID);

    // get all ingredients of a post
    @Query(value = "SELECT * FROM contains_ingredient i WHERE i.post_id = :postID", nativeQuery = true)
    Set<ContainsIngredient> getPostIngredients(@Param("postID") Long id);
}
