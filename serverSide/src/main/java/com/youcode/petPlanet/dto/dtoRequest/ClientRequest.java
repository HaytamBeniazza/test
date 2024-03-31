package com.youcode.petPlanet.dto.dtoRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@AllArgsConstructor
public class ClientRequest extends UserRequest{

    private String phone;
    private String address;
}
