package com.youcode.petPlanet.controller;

import com.youcode.petPlanet.dto.dtoRequest.PetProductRequest;
import com.youcode.petPlanet.dto.dtoResponse.PetProductResponse;
import com.youcode.petPlanet.dto.dtoResponse.ProductResponse;
import com.youcode.petPlanet.service.serviceImpl.PetProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/petproduct")
@Validated
public class PetProductController {
    private final PetProductServiceImpl petProductService;

    public PetProductController(PetProductServiceImpl productService) {
        this.petProductService = productService;
    }


    @PostMapping("/add")
    public ResponseEntity<PetProductResponse> Add(@RequestBody @Valid PetProductRequest petProductRequest){
        Optional<PetProductResponse> savedPetProduct = petProductService.Add(petProductRequest);
        return ResponseEntity.ok(savedPetProduct.get());
    }

    @GetMapping("/list")
    public ResponseEntity<List<PetProductResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        List<PetProductResponse> petProducts = petProductService.getAll(page,size);
        return ResponseEntity.ok(petProducts);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PetProductResponse> findById(@PathVariable Long id){
        Optional<PetProductResponse> petProduct = petProductService.findById(id);
        return ResponseEntity.ok(petProduct.get());
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<PetProductResponse>> delete(@PathVariable Long id){
        Optional<PetProductResponse> petProductResponse = petProductService.delete(id);
        return ResponseEntity.ok(petProductResponse);
    }

    @GetMapping("pages/{size}")
    public ResponseEntity<Long> getNumberOfPages(@PathVariable Integer size){
        return ResponseEntity.ok(petProductService.getTotalPages(size));
    }

}
