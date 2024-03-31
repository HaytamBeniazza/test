package com.youcode.petPlanet.dto.dtoRequest;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {

    private Long id;
    @NotBlank(message = "name must not be empty")
    private String name;
    @NotBlank(message = "description must not be empty")
    private String description;
    private String image;

}
