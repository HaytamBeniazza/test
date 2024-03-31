package com.youcode.petPlanet.dto.dtoRequest;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {

    @Min(0)
    private int value;
    @NotBlank(message = "comment must not be empty")
    private String comment;
    @NotNull
    private Long userId;
}
