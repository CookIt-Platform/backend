package com.cookit.backend.service;

import com.cookit.backend.dto.IngredientDto;
import com.cookit.backend.entity.Ingredient;
import java.util.List;
import java.util.Set;


public interface IngredientService {

    public Set<String> getAllIngredients();
    public Ingredient getIngredient(String name);
    public void createIngredient(IngredientDto ingredientDto);
    public void updateIngredient(String newName, String oldName);
    public void deleteIngredient(String name);
}
