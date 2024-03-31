package com.youcode.petPlanet.dto.dtoRequest;

import com.youcode.petPlanet.entity.Product;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetRequest {

    private Long id;
    @NotBlank(message = "name must not be empty")
    private String name;
    @NotBlank(message = "description must not be empty")
    private String description;
}
