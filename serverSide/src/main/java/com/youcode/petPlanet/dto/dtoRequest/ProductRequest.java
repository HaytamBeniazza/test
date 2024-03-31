package com.youcode.petPlanet.dto.dtoRequest;

import com.youcode.petPlanet.entity.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private Long id;
    @NotBlank(message = "name must not be empty")
    private String name;
    @NotBlank(message = "description must not be empty")
    private String description;
    private int quantity;

    private MultipartFile image;

    private BigDecimal price;

}
