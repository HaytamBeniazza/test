package com.youcode.petPlanet.service.serviceInterface;

import com.youcode.petPlanet.dto.dtoRequest.PetProductRequest;
import com.youcode.petPlanet.dto.dtoRequest.PetRequest;
import com.youcode.petPlanet.dto.dtoResponse.OrderResponse;
import com.youcode.petPlanet.dto.dtoResponse.PetProductResponse;
import com.youcode.petPlanet.dto.dtoResponse.PetResponse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PetProductService {
    Optional<PetProductResponse> Add(PetProductRequest petProductRequest);
    List<PetProductResponse> getAll(int page, int size);
    Optional<PetProductResponse> delete(Long id);
    Optional<PetProductResponse> findById(Long id);
    long getTotalPages(Integer size);
}
