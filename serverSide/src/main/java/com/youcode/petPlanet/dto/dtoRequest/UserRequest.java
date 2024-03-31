package com.youcode.petPlanet.dto.dtoRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {


    protected String fullName;
    protected String email;
    protected String password;
}
