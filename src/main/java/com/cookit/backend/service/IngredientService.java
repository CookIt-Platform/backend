package com.cookit.backend.service;

import com.cookit.backend.entity.Ingredient;
import java.util.List;


public interface IngredientService {

    public List<Ingredient> getAllIngredients();
    public Ingredient getIngredient(String name);
    public void createIngredient(Ingredient ingredient);
    public void updateIngredient(Ingredient ingredient);
    public void deleteIngredient(String name);
}
