package com.youcode.petPlanet.repository;

import com.youcode.petPlanet.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
