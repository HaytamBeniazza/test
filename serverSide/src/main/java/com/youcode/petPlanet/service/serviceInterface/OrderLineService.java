package com.youcode.petPlanet.service.serviceInterface;

import com.youcode.petPlanet.dto.dtoRequest.OrderLineRequest;
import com.youcode.petPlanet.dto.dtoRequest.OrderRequest;
import com.youcode.petPlanet.dto.dtoResponse.OrderLineResponse;
import com.youcode.petPlanet.dto.dtoResponse.OrderResponse;

import java.util.List;
import java.util.Optional;

public interface OrderLineService {

    Optional<OrderLineResponse> Add(OrderLineRequest orderLine);
    Optional<OrderLineResponse> findById(Long id);
    List<OrderLineResponse> getAll(int page, int size);
    Optional<OrderLineResponse> delete(Long id);
}
