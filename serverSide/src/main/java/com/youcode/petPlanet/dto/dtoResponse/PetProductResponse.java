package com.youcode.petPlanet.dto.dtoResponse;

import com.youcode.petPlanet.dto.dtoRequest.CategoryRequest;
import com.youcode.petPlanet.dto.dtoRequest.PetRequest;
import com.youcode.petPlanet.dto.dtoRequest.ProductRequest;
import com.youcode.petPlanet.entity.Category;
import com.youcode.petPlanet.entity.Pet;
import com.youcode.petPlanet.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetProductResponse {
    private Long id;
    private PetResponse pet;
    private ProductResponse product;
    private CategoryResponse category;
}
