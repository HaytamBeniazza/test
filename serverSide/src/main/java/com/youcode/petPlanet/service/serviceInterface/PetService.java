package com.youcode.petPlanet.service.serviceInterface;

import com.youcode.petPlanet.dto.dtoRequest.PetRequest;
import com.youcode.petPlanet.dto.dtoResponse.PetResponse;

import java.util.List;
import java.util.Optional;

public interface PetService {

    Optional<PetResponse> Add(PetRequest pet);
    Optional<PetResponse> findByName(String name);
    Optional<PetResponse> findById(Long id);
    List<PetResponse> getAll(int page, int size);
    Optional<PetResponse> update(Long id, PetRequest fish);
    Optional<PetResponse> delete(Long id);
}
