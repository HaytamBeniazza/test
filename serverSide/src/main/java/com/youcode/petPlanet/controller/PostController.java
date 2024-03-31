package com.youcode.petPlanet.controller;


import com.youcode.petPlanet.dto.dtoRequest.PostRequest;
import com.youcode.petPlanet.dto.dtoRequest.ProductRequest;
import com.youcode.petPlanet.dto.dtoResponse.PostResponse;
import com.youcode.petPlanet.dto.dtoResponse.ProductResponse;
import com.youcode.petPlanet.service.serviceImpl.PostServiceImpl;
import com.youcode.petPlanet.service.serviceInterface.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostServiceImpl postService;

    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @PostMapping("/add")
    public ResponseEntity<PostResponse> Add(@RequestBody PostRequest postRequest){
        Optional<PostResponse> savedPost = postService.Add(postRequest);
        return ResponseEntity.ok(savedPost.get());
    }

    @GetMapping("/list")
    public ResponseEntity<List<PostResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        List<PostResponse> posts = postService.getAll(page,size);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PostResponse> findById(@PathVariable Long id){
        Optional<PostResponse> post = postService.findById(id);
        return ResponseEntity.ok(post.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<PostResponse>> delete(@PathVariable Long id){
        Optional<PostResponse> post = postService.delete(id);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<PostResponse>> update(@PathVariable Long id, @RequestBody PostRequest post){
        Optional<PostResponse> updatedPost = postService.update(id, post);
        return ResponseEntity.ok(updatedPost);
    }
}
