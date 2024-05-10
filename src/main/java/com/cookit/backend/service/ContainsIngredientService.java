package com.cookit.backend.service;

import com.cookit.backend.dto.ContainsIngredientDto;
import com.cookit.backend.dto.IngredientDto;

import java.util.Set;

public interface ContainsIngredientService {
    void createContainsIngredient(ContainsIngredientDto ingredientDto);
    void deleteContainsIngredient(ContainsIngredientDto ingredientDto);
    Set<IngredientDto> getPostIngredients(Long id);
}
