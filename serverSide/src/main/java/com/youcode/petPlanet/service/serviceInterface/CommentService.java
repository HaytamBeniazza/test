package com.youcode.petPlanet.service.serviceInterface;

import com.youcode.petPlanet.dto.dtoRequest.CommentRequest;
import com.youcode.petPlanet.dto.dtoRequest.PetRequest;
import com.youcode.petPlanet.dto.dtoResponse.CommentResponse;
import com.youcode.petPlanet.dto.dtoResponse.PetResponse;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Optional<CommentResponse> Add(CommentRequest comment);
    Optional<CommentResponse> findById(Long id);
    List<CommentResponse> getAll(int page, int size);
    Optional<CommentResponse> update(Long id, CommentRequest comment);
    Optional<CommentResponse> delete(Long id);
}
