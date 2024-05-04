package com.cookit.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cookit.backend.entity.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, String>{

    @Query("SELECT i FROM Ingredient i WHERE LOWER(i.ingredientName) = LOWER(:name)")
    Ingredient findByNameCaseInsensitive(@Param("name") String name);
}
