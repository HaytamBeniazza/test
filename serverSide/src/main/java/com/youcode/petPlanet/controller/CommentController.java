package com.youcode.petPlanet.controller;


import com.youcode.petPlanet.dto.dtoRequest.CommentRequest;
import com.youcode.petPlanet.dto.dtoRequest.PetRequest;
import com.youcode.petPlanet.dto.dtoResponse.CommentResponse;
import com.youcode.petPlanet.dto.dtoResponse.PetResponse;
import com.youcode.petPlanet.service.serviceImpl.CommentServiceImpl;
import com.youcode.petPlanet.service.serviceInterface.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add")
    public ResponseEntity<CommentResponse> Add(@RequestBody CommentRequest comment){
        Optional<CommentResponse> savedComment = commentService.Add(comment);
        return ResponseEntity.ok(savedComment.get());
    }

    @GetMapping("/list")
    public ResponseEntity<List<CommentResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        List<CommentResponse> comments = commentService.getAll(page,size);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CommentResponse> findById(@PathVariable Long id){
        Optional<CommentResponse> comment = commentService.findById(id);
        return ResponseEntity.ok(comment.get());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<CommentResponse>> delete(@PathVariable Long id){
        Optional<CommentResponse> comment = commentService.delete(id);
        return ResponseEntity.ok(comment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<CommentResponse>> update(@PathVariable Long id, @RequestBody CommentRequest comment){
        Optional<CommentResponse> updatedComment = commentService.update(id,comment);
        return ResponseEntity.ok(updatedComment);
    }
}
