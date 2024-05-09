package com.cookit.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contains_ingredient")
@IdClass(ContainsIngredientId.class)
public class ContainsIngredient {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    @JoinColumn(name = "post_id", referencedColumnName = "Id", foreignKey = @ForeignKey(name = "FK_post_id_contains_ingredient"))
    private Post postId;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    @JoinColumn(name = "ingredient_name", referencedColumnName = "ingredient_name", foreignKey = @ForeignKey(name = "FK_ingredient_name_contains_ingredient"))
    private Ingredient ingredientName;

    @Column(name = "quantity")
    private double quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit"/*, columnDefinition = "ENUM('g', 'kg', 'ml', 'l', 'tbsp', 'tsp', 'cup', 'piece')"*/)
    private MeasurementUnit unit;
}
