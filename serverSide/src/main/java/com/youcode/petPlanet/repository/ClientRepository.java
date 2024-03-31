package com.youcode.petPlanet.repository;

import com.youcode.petPlanet.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}