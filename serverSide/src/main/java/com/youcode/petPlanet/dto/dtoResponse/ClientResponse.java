package com.youcode.petPlanet.dto.dtoResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientResponse extends UserResponse{
    private Long id;
    private String phone;
    private String address;
}
