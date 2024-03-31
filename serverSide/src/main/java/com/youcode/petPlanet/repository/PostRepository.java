package com.youcode.petPlanet.repository;

import com.youcode.petPlanet.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
