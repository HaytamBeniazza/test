package com.youcode.petPlanet.service.serviceImpl;

import com.youcode.petPlanet.dto.dtoRequest.CommentRequest;
import com.youcode.petPlanet.dto.dtoResponse.CommentResponse;
import com.youcode.petPlanet.dto.dtoResponse.PetResponse;
import com.youcode.petPlanet.entity.Comment;
import com.youcode.petPlanet.entity.Pet;
import com.youcode.petPlanet.exception.ResourceNotFoundException;
import com.youcode.petPlanet.repository.CommentRepository;
import com.youcode.petPlanet.service.serviceInterface.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<CommentResponse> Add(CommentRequest comment) {
        Comment commentToSave = modelMapper.map(comment , Comment.class);
        commentRepository.save(commentToSave);
        return Optional.of(modelMapper.map(comment, CommentResponse.class));
    }

    @Override
    public Optional<CommentResponse> findById(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isPresent()){
            return Optional.of(modelMapper.map(comment,CommentResponse.class));
        }else{
            throw new ResourceNotFoundException("Comment not found with ID : " + id);
        }
    }

    @Override
    public List<CommentResponse> getAll(int page, int size) {
        Page<Comment> commentsPage = commentRepository.findAll(PageRequest.of(page, size));
        List<Comment> comments = commentsPage.getContent();
        if(comments.isEmpty()){
            comments = commentRepository.findAll();
        }
        return comments.stream()
                .map(comment -> modelMapper.map(comment, CommentResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CommentResponse> update(Long id, CommentRequest comment) {
        Optional<Comment> commentToUpdate = commentRepository.findById(id);
        if(commentToUpdate.isPresent()){
            commentToUpdate.get().setBody(comment.getBody());
            commentRepository.save(commentToUpdate.get());
            return Optional.ofNullable(modelMapper.map(commentToUpdate, CommentResponse.class));
        }else{
            throw new ResourceNotFoundException("Comment not found with name : " + id);
        }
    }

    @Override
    public Optional<CommentResponse> delete(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isPresent()){
            commentRepository.delete(comment.get());
            return Optional.of(modelMapper.map(comment, CommentResponse.class));
        }else{
            throw new ResourceNotFoundException("Comment not found with name : " + id);
        }
    }
}
