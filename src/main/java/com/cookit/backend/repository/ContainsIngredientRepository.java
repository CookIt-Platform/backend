package com.cookit.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cookit.backend.entity.ContainsIngredient;
import com.cookit.backend.entity.ContainsIngredientId;

public interface ContainsIngredientRepository extends JpaRepository<ContainsIngredient, ContainsIngredientId>{

}
