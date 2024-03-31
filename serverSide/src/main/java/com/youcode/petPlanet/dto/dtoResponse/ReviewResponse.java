package com.youcode.petPlanet.dto.dtoResponse;

import com.youcode.petPlanet.dto.dtoRequest.UserRequest;
import lombok.Data;

@Data
public class ReviewResponse {
    private Long id;
    private int value;
    private String comment;
    private UserRequest user;
}
