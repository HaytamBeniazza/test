package com.youcode.petPlanet.dto.dtoRequest;

import com.youcode.petPlanet.entity.PetProduct;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineRequest {

    private Long id;
    @Min(0)
    private Long quantity;
    private PetProduct petProduct;
    @DateTimeFormat
    private LocalDateTime dateTime;
}