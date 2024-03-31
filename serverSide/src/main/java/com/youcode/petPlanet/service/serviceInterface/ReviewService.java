package com.youcode.petPlanet.service.serviceInterface;

import com.youcode.petPlanet.dto.dtoRequest.ReviewRequest;
import com.youcode.petPlanet.dto.dtoResponse.ReviewResponse;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    Optional<ReviewResponse> Add(ReviewRequest review);
    Optional<ReviewResponse> findById(Long id);
    List<ReviewResponse> getAll(int page, int size);
    Optional<ReviewResponse> update(Long id, ReviewRequest review);
    Optional<ReviewResponse> delete(Long id);
}
