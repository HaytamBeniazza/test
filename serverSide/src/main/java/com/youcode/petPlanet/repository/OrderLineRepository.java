package com.youcode.petPlanet.repository;

import com.youcode.petPlanet.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
}
