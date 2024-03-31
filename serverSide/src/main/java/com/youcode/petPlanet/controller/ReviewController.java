package com.youcode.petPlanet.controller;


import com.youcode.petPlanet.dto.dtoRequest.ProductRequest;
import com.youcode.petPlanet.dto.dtoRequest.ReviewRequest;
import com.youcode.petPlanet.dto.dtoResponse.ProductResponse;
import com.youcode.petPlanet.dto.dtoResponse.ReviewResponse;
import com.youcode.petPlanet.repository.ReviewRepository;
import com.youcode.petPlanet.service.serviceImpl.ReviewServiceImpl;
import com.youcode.petPlanet.service.serviceInterface.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewServiceImpl reviewService;

    public ReviewController(ReviewServiceImpl reviewService) {
        this.reviewService = reviewService;
    }


    @PostMapping("/add")
    public ResponseEntity<ReviewResponse> Add(@RequestBody ReviewRequest reviewRequest){
        Optional<ReviewResponse> savedReview = reviewService.Add(reviewRequest);
        return ResponseEntity.ok(savedReview.get());
    }

    @GetMapping("/list")
    public ResponseEntity<List<ReviewResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        List<ReviewResponse> reviews = reviewService.getAll(page,size);
        return ResponseEntity.ok(reviews);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> findById(@PathVariable Long id){
        Optional<ReviewResponse> review = reviewService.findById(id);
        return ResponseEntity.ok(review.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<ReviewResponse>> delete(@PathVariable Long id){
        Optional<ReviewResponse> review = reviewService.delete(id);
        return ResponseEntity.ok(review);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<ReviewResponse>> update(@PathVariable Long id, @RequestBody ReviewRequest review){
        Optional<ReviewResponse> updatedReview = reviewService.update(id, review);
        return ResponseEntity.ok(updatedReview);
    }
}
