package com.youcode.petPlanet.repository;

import com.youcode.petPlanet.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}