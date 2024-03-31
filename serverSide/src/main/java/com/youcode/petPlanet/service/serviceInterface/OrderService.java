package com.youcode.petPlanet.service.serviceInterface;

import com.youcode.petPlanet.dto.dtoRequest.OrderRequest;
import com.youcode.petPlanet.dto.dtoRequest.PetRequest;
import com.youcode.petPlanet.dto.dtoResponse.OrderResponse;
import com.youcode.petPlanet.dto.dtoResponse.PetResponse;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    void add(OrderRequest order);
    Optional<OrderResponse> findById(Long id);
    List<OrderResponse> getAll(int page, int size);
    Optional<OrderResponse> delete(Long id);

    Optional<OrderResponse> changeStatus(Long id, String newStatus);
}
