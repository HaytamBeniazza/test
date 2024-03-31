package com.youcode.petPlanet.service.serviceInterface;

import com.youcode.petPlanet.dto.dtoRequest.PetRequest;
import com.youcode.petPlanet.dto.dtoRequest.PostRequest;
import com.youcode.petPlanet.dto.dtoResponse.PetResponse;
import com.youcode.petPlanet.dto.dtoResponse.PostResponse;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Optional<PostResponse> Add(PostRequest fish);
    Optional<PostResponse> findByName(String name);
    Optional<PostResponse> findById(Long id);
    List<PostResponse> getAll(int page, int size);
    Optional<PostResponse> update(Long id, PostRequest fish);
    Optional<PostResponse> delete(Long id);
}
