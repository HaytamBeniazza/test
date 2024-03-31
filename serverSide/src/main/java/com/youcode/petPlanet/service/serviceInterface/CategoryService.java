package com.youcode.petPlanet.service.serviceInterface;

import com.youcode.petPlanet.dto.dtoRequest.CategoryRequest;
import com.youcode.petPlanet.dto.dtoRequest.CommentRequest;
import com.youcode.petPlanet.dto.dtoResponse.CategoryResponse;
import com.youcode.petPlanet.dto.dtoResponse.CommentResponse;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Optional<CategoryResponse> Add(CategoryRequest category);
    Optional<CategoryResponse> findById(Long id);
    List<CategoryResponse> getAll(int page, int size);
    Optional<CategoryResponse> update(Long id, CategoryRequest category);
    Optional<CategoryResponse> delete(Long id);
}
