package com.youcode.petPlanet.repository;

import com.youcode.petPlanet.dto.dtoResponse.PetResponse;
import com.youcode.petPlanet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {
    Optional<Pet> findByName(String name);
}
