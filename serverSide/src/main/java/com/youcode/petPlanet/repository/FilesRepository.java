package com.youcode.petPlanet.repository;

import com.youcode.petPlanet.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesRepository extends JpaRepository<Files, Long> {
    Files findByName(String name);
}