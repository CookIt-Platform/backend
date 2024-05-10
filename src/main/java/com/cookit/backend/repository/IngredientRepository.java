package com.cookit.backend.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cookit.backend.entity.Ingredient;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, String>{

    @Query("SELECT i FROM Ingredient i WHERE LOWER(i.ingredientName) = LOWER(:name)")
    Ingredient findByNameCaseInsensitive(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ingredient(ingredient_name) VALUES(:name)", nativeQuery = true)
    void createIngredient(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ingredient WHERE LOWER(ingredient_name) = LOWER(:name)", nativeQuery = true)
    void deleteIngredient(@Param("name") String name);

    @Query(value = "SELECT ingredient_name FROM ingredient", nativeQuery = true)
    Set<String> getAllIngredients();

    @Modifying
    @Transactional
    @Query(value = "UPDATE ingredient SET ingredient_name = :newName WHERE ingredient_name = :oldName", nativeQuery = true)
    void updateIngredient(@Param("newName") String newName, @Param("oldName") String oldName);
}
