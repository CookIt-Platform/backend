package com.cookit.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cookit.backend.entity.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, String>{

}
