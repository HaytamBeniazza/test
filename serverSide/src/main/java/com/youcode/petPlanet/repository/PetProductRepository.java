package com.youcode.petPlanet.repository;

import com.youcode.petPlanet.entity.Order;
import com.youcode.petPlanet.entity.PetProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetProductRepository extends JpaRepository<PetProduct, Long> {
    Optional<PetProduct> findByProductId(Long productId);
}
