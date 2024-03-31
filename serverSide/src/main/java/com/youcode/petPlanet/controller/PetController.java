package com.youcode.petPlanet.controller;


import com.youcode.petPlanet.dto.dtoRequest.PetRequest;
import com.youcode.petPlanet.dto.dtoResponse.PetResponse;
import com.youcode.petPlanet.service.serviceImpl.PetServiceImpl;
import com.youcode.petPlanet.service.serviceInterface.PetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetServiceImpl petService;

    public PetController(PetServiceImpl petService) {
        this.petService = petService;
    }


    @PostMapping("/add")
    public ResponseEntity<PetResponse> Add( @RequestBody @Valid PetRequest pet){
        Optional<PetResponse> savedFish = petService.Add(pet);
        return ResponseEntity.ok(savedFish.get());
    }

    @GetMapping("/list")
    public ResponseEntity<List<PetResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        List<PetResponse> pets = petService.getAll(page,size);
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PetResponse> findById(@PathVariable Long id){
        Optional<PetResponse> pet = petService.findById(id);
        return ResponseEntity.ok(pet.get());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PetResponse> findByName(@PathVariable String name){
        Optional<PetResponse> pet = petService.findByName(name);
        return ResponseEntity.ok(pet.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id){
        Optional<PetResponse> pet = petService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Resource deleted successfully.");
        response.put("deletedElementIdentifier", id.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<PetResponse>> update(@PathVariable Long id, @RequestBody PetRequest pet){
        Optional<PetResponse> updatedPet = petService.update(id,pet);
        return ResponseEntity.ok(updatedPet);
    }

}
