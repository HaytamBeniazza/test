package com.youcode.petPlanet.service.serviceImpl;

import com.youcode.petPlanet.dto.dtoRequest.ReviewRequest;
import com.youcode.petPlanet.dto.dtoResponse.PetResponse;
import com.youcode.petPlanet.dto.dtoResponse.ProductResponse;
import com.youcode.petPlanet.dto.dtoResponse.ReviewResponse;
import com.youcode.petPlanet.entity.Pet;
import com.youcode.petPlanet.entity.Product;
import com.youcode.petPlanet.entity.Review;
import com.youcode.petPlanet.exception.ResourceNotFoundException;
import com.youcode.petPlanet.repository.ReviewRepository;
import com.youcode.petPlanet.service.serviceInterface.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ModelMapper modelMapper) {
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<ReviewResponse> Add(ReviewRequest review) {
        Review reviewToSave = modelMapper.map(review , Review.class);
        reviewRepository.save(reviewToSave);
        return Optional.of(modelMapper.map(review, ReviewResponse.class));
    }

    @Override
    public Optional<ReviewResponse> findById(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isPresent()){
            return Optional.of(modelMapper.map(review, ReviewResponse.class));
        }else{
            throw new ResourceNotFoundException("Review not found with ID : " + id);
        }
    }

    @Override
    public List<ReviewResponse> getAll(int page, int size) {
        Page<Review> reviewsPage = reviewRepository.findAll(PageRequest.of(page, size));
        List<Review> reviews = reviewsPage.getContent();
        if(reviews.isEmpty()){
            reviews = reviewRepository.findAll();
        }
        return reviews.stream()
                .map(review -> modelMapper.map(review, ReviewResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReviewResponse> update(Long id, ReviewRequest review) {
        Optional<Review> reviewToUpdate = reviewRepository.findById(id);
        if(reviewToUpdate.isPresent()){
            reviewToUpdate.get().setComment(review.getComment());
            reviewToUpdate.get().setValue(review.getValue());
            reviewRepository.save(reviewToUpdate.get());
            return Optional.ofNullable(modelMapper.map(reviewToUpdate, ReviewResponse.class));
        }else{
            throw new ResourceNotFoundException("Review not found with name : " + id);
        }
    }

    @Override
    public Optional<ReviewResponse> delete(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isPresent()){
            reviewRepository.delete(review.get());
            return Optional.of(modelMapper.map(review, ReviewResponse.class));
        }else{
            throw new ResourceNotFoundException("Review not found with name : " + id);
        }
    }
}
