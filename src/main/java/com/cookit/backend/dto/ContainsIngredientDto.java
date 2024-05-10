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
public class ContainsIngredientDto {
    private String name;
    private Double quantity;
    private MeasurementUnit unit;
    Long postId;
}
