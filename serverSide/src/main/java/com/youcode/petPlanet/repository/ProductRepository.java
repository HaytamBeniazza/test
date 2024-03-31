package com.youcode.petPlanet.repository;

import com.youcode.petPlanet.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
