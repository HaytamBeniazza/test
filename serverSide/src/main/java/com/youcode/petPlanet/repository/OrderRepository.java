package com.youcode.petPlanet.repository;

import com.youcode.petPlanet.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
