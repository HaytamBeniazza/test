package com.youcode.petPlanet.repository;

import com.youcode.petPlanet.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
