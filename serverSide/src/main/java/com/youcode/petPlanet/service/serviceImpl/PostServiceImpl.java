package com.youcode.petPlanet.service.serviceImpl;

import com.youcode.petPlanet.dto.dtoRequest.PetRequest;
import com.youcode.petPlanet.dto.dtoRequest.PostRequest;
import com.youcode.petPlanet.dto.dtoResponse.PetResponse;
import com.youcode.petPlanet.dto.dtoResponse.PostResponse;
import com.youcode.petPlanet.entity.Pet;
import com.youcode.petPlanet.entity.Post;
import com.youcode.petPlanet.exception.ResourceNotFoundException;
import com.youcode.petPlanet.repository.PostRepository;
import com.youcode.petPlanet.service.serviceInterface.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final ModelMapper modelMapper;
    private final PostRepository postRepository;

    public PostServiceImpl(ModelMapper modelMapper, PostRepository postRepository) {
        this.modelMapper = modelMapper;
        this.postRepository = postRepository;
    }

    @Override
    public Optional<PostResponse> Add(PostRequest post) {
        Post postToSave = modelMapper.map(post , Post.class);
        postRepository.save(postToSave);
        return Optional.of(modelMapper.map(post, PostResponse.class));
    }

    @Override
    public Optional<PostResponse> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<PostResponse> findById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent()){
            return Optional.of(modelMapper.map(post,PostResponse.class));
        }else{
            throw new ResourceNotFoundException("Post not found with ID : " + id);
        }
    }

    @Override
    public List<PostResponse> getAll(int page, int size) {
        Page<Post> postPage = postRepository.findAll(PageRequest.of(page, size));
        List<Post> posts = postPage.getContent();
        if(posts.isEmpty()){
            posts = postRepository.findAll();
        }
        return posts.stream()
                .map(post -> modelMapper.map(post, PostResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PostResponse> update(Long id, PostRequest post) {
        Optional<Post> postToUpdate = postRepository.findById(id);
        if(postToUpdate.isPresent()){
            postToUpdate.get().setTitle(post.getTitle());
            postToUpdate.get().setContent(post.getContent());
            postToUpdate.get().setImage(post.getImage());
            postRepository.save(postToUpdate.get());
            return Optional.ofNullable(modelMapper.map(postToUpdate, PostResponse.class));
        }else{
            throw new ResourceNotFoundException("Post not found with name : " + id);
        }
    }

    @Override
    public Optional<PostResponse> delete(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent()){
            postRepository.delete(post.get());
            return Optional.of(modelMapper.map(post, PostResponse.class));
        }else{
            throw new ResourceNotFoundException("Post not found with name : " + id);
        }
    }
}
