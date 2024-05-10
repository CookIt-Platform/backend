package com.cookit.backend.controller;

import com.cookit.backend.dto.ContainsIngredientDto;
import com.cookit.backend.dto.IngredientDto;
import com.cookit.backend.entity.ContainsIngredient;
import com.cookit.backend.service.ContainsIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/containsingredient")
public class ContainsIngredientController {
    private final ContainsIngredientService containsIngredientService;

    @Autowired
    public ContainsIngredientController(ContainsIngredientService containsIngredientService) {
        this.containsIngredientService = containsIngredientService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createContainsIngredient(@RequestBody ContainsIngredientDto ingredientDto) {
        containsIngredientService.createContainsIngredient(ingredientDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteContainsIngredient(@RequestBody ContainsIngredientDto ingredientDto) {
        containsIngredientService.deleteContainsIngredient(ingredientDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/ingredients/post/{id}")
    public Set<IngredientDto> getPostIngredients(@PathVariable Long id) {
        return containsIngredientService.getPostIngredients(id);
    }
}
