package com.cookit.backend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cookit.backend.entity.Ingredient;
import com.cookit.backend.repository.IngredientRepository;
import com.cookit.backend.service.IngredientService;

@Service
public class IngredientServiceImpl implements IngredientService{

    private IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient getIngredient(String name) {
        return ingredientRepository.findByNameCaseInsensitive(name);
    }

    @Override
    public void createIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    @Override
    public void deleteIngredient(String name) {
        ingredientRepository.deleteById(name);
    }

}
