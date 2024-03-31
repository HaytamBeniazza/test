package com.youcode.petPlanet.controller;

import com.youcode.petPlanet.dto.dtoRequest.CategoryRequest;
import com.youcode.petPlanet.dto.dtoRequest.CommentRequest;
import com.youcode.petPlanet.dto.dtoResponse.CategoryResponse;
import com.youcode.petPlanet.dto.dtoResponse.CommentResponse;
import com.youcode.petPlanet.service.serviceImpl.CategoryServiceImpl;
import com.youcode.petPlanet.service.serviceInterface.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<CategoryResponse> Add(@Valid @RequestBody CategoryRequest categoryRequest){
        Optional<CategoryResponse> savedCat = categoryService.Add(categoryRequest);
        return ResponseEntity.ok(savedCat.get());
    }

    @GetMapping("/list")
    public ResponseEntity<List<CategoryResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        List<CategoryResponse> cats = categoryService.getAll(page,size);
        return ResponseEntity.ok(cats);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id){
        Optional<CategoryResponse> cat = categoryService.findById(id);
        return ResponseEntity.ok(cat.get());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<CategoryResponse>> delete(@PathVariable Long id){
        Optional<CategoryResponse> cat = categoryService.delete(id);
        return ResponseEntity.ok(cat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<CategoryResponse>> update(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest){
        Optional<CategoryResponse> updatedCat = categoryService.update(id,categoryRequest);
        return ResponseEntity.ok(updatedCat);
    }
}
