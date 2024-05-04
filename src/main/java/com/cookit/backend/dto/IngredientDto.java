package com.cookit.backend.dto;


import com.cookit.backend.entity.MeasurementUnit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDto {
    private String name;
    private double quantity;
    private MeasurementUnit unit;
/*
    public Ingredient convertDtoToIngredientEntity(IngredientDto ingredientDto){
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientName(ingredientDto.getIngredientName());
        return ingredient;   
    } 

    public ContainsIngredient convertDtoToContainsIngredientEntity(IngredientDto ingredientDto){
        ContainsIngredient containsIngredient = new ContainsIngredient();
        containsIngredient.setIngredientName(convertDtoToIngredientEntity(ingredientDto));
        containsIngredient.setQuantity(ingredientDto.getQuantity());
        containsIngredient.setUnit(ingredientDto.getUnit());
        return containsIngredient;
    }
    */
}
