package com.youcode.petPlanet.service.serviceImpl;

import com.youcode.petPlanet.dto.dtoRequest.CategoryRequest;
import com.youcode.petPlanet.dto.dtoResponse.CategoryResponse;
import com.youcode.petPlanet.entity.Category;
import com.youcode.petPlanet.exception.ResourceNotFoundException;
import com.youcode.petPlanet.repository.CategoryRepository;
import com.youcode.petPlanet.service.serviceInterface.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<CategoryResponse> Add(CategoryRequest category) {
        Category categoryToSave = modelMapper.map(category , Category.class);
        categoryRepository.save(categoryToSave);
        return Optional.of(modelMapper.map(category, CategoryResponse.class));
    }

    @Override
    public Optional<CategoryResponse> findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            return Optional.of(modelMapper.map(category,CategoryResponse.class));
        }else{
            throw new ResourceNotFoundException("Category not found with ID : " + id);
        }
    }

    @Override
    public List<CategoryResponse> getAll(int page, int size) {
        Page<Category> categoriesPage = categoryRepository.findAll(PageRequest.of(page, size));
        List<Category> categories = categoriesPage.getContent();
        if(categories.isEmpty()){
            categories = categoryRepository.findAll();
        }
        return categories.stream()
                .map(category -> modelMapper.map(category, CategoryResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryResponse> update(Long id, CategoryRequest category) {
        Optional<Category> categoryToUpdate = categoryRepository.findById(id);
        if(categoryToUpdate.isPresent()){
            categoryToUpdate.get().setDescription(category.getDescription());
            categoryToUpdate.get().setName(category.getName());
            categoryToUpdate.get().setImage(category.getImage());
            categoryRepository.save(categoryToUpdate.get());
            return Optional.ofNullable(modelMapper.map(categoryToUpdate, CategoryResponse.class));
        }else{
            throw new ResourceNotFoundException("Category not found with name : " + id);
        }
    }

    @Override
    public Optional<CategoryResponse> delete(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            categoryRepository.delete(category.get());
            return Optional.of(modelMapper.map(category, CategoryResponse.class));
        }else{
            throw new ResourceNotFoundException("Category not found with name : " + id);
        }
    }
}
