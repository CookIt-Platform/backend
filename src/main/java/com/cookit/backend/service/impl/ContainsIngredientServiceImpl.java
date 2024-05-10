package com.cookit.backend.service.impl;

import com.cookit.backend.dto.ContainsIngredientDto;
import com.cookit.backend.dto.IngredientDto;
import com.cookit.backend.entity.ContainsIngredient;
import com.cookit.backend.repository.ContainsIngredientRepository;
import com.cookit.backend.repository.IngredientRepository;
import com.cookit.backend.service.ContainsIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ContainsIngredientServiceImpl implements ContainsIngredientService{
    private final ContainsIngredientRepository containsIngredientRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public ContainsIngredientServiceImpl(ContainsIngredientRepository containsIngredientRepository, IngredientRepository ingredientRepository) {
        this.containsIngredientRepository = containsIngredientRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void createContainsIngredient(ContainsIngredientDto ingredientDto) {
        if (ingredientRepository.findByNameCaseInsensitive(ingredientDto.getName()) == null) {
            ingredientRepository.createIngredient(ingredientDto.getName());
        }
        containsIngredientRepository.createContainsIngredient(ingredientDto.getName(), ingredientDto.getPostId(), ingredientDto.getQuantity(), ingredientDto.getUnit().toString());
    }

    @Override
    public void deleteContainsIngredient(ContainsIngredientDto ingredientDto) {
        containsIngredientRepository.deleteContainsIngredient(ingredientDto.getName(), ingredientDto.getPostId());
    }

    @Override
    public Set<IngredientDto> getPostIngredients(Long id) {
        Set<ContainsIngredient> ingredients = containsIngredientRepository.getPostIngredients(id);
        Set<IngredientDto> ingredientDtos = new HashSet<>();
        for (ContainsIngredient i : ingredients) {
            IngredientDto ingredientDto = new IngredientDto(i.getIngredientName().getIngredientName(), i.getQuantity(), i.getUnit());
            ingredientDtos.add(ingredientDto);
        }
        return ingredientDtos;
    }
}
