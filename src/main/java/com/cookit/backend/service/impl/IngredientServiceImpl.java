package com.cookit.backend.service.impl;

import java.util.List;
import java.util.Set;

import com.cookit.backend.dto.IngredientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cookit.backend.entity.Ingredient;
import com.cookit.backend.repository.IngredientRepository;
import com.cookit.backend.service.IngredientService;

@Service
public class IngredientServiceImpl implements IngredientService{

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Set<String> getAllIngredients() {
        return ingredientRepository.getAllIngredients();
    }

    @Override
    public Ingredient getIngredient(String name) {
        return ingredientRepository.findByNameCaseInsensitive(name);
    }

    @Override
    public void createIngredient(IngredientDto ingredientDto) {
        if (ingredientRepository.findByNameCaseInsensitive(ingredientDto.getName()) == null)
            ingredientRepository.createIngredient(ingredientDto.getName());
    }

    @Override
    public void updateIngredient(String newName, String oldName) {
        ingredientRepository.updateIngredient(newName, oldName);
    }

    @Override
    public void deleteIngredient(String name) {
        ingredientRepository.deleteIngredient(name);
    }

}
