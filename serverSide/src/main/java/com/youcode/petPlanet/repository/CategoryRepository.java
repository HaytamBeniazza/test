package com.youcode.petPlanet.repository;

import com.youcode.petPlanet.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
