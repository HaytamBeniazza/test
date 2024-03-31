package com.youcode.petPlanet.repository;

import com.youcode.petPlanet.entity.BlogAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogAuthorRepository extends JpaRepository<BlogAuthor, Long> {
}