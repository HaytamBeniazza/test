package com.youcode.petPlanet.dto.dtoRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetProductRequest {
    private Long id;
    @NotNull(message = "pet id must not be empty")
    private Long petId;

    @NotNull(message = "product id must not be empty")
    private Long productId;

    @NotNull(message = "category id must not be empty")
    private Long categoryId;
}
