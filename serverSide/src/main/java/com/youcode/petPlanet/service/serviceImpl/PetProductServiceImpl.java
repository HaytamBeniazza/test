package com.youcode.petPlanet.service.serviceImpl;

import com.youcode.petPlanet.dto.dtoRequest.PetProductRequest;
import com.youcode.petPlanet.dto.dtoResponse.PetProductResponse;
import com.youcode.petPlanet.entity.Pet;
import com.youcode.petPlanet.entity.PetProduct;
import com.youcode.petPlanet.entity.Product;
import com.youcode.petPlanet.exception.ResourceNotFoundException;
import com.youcode.petPlanet.repository.PetProductRepository;
import com.youcode.petPlanet.repository.ProductRepository;
import com.youcode.petPlanet.service.serviceInterface.PetProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetProductServiceImpl implements PetProductService {

    private final ModelMapper modelMapper;
    private final PetProductRepository petProductRepository;
    private final ProductRepository productRepository;

    public PetProductServiceImpl(ModelMapper modelMapper, PetProductRepository petProductRepository, ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.petProductRepository = petProductRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Optional<PetProductResponse> Add(PetProductRequest petProductRequest) {
        Optional<Product> checkProduct = productRepository.findById(petProductRequest.getProductId());
        if(checkProduct.isEmpty()){
            throw new ResourceNotFoundException("Product not found with id : " + petProductRequest.getProductId());
        }
//        Optional<PetProduct> checkPetProduct = petProductRepository.findByProductId(petProductRequest.getProductId());
//        if(checkPetProduct.isEmpty()){
//            throw new ResourceNotFoundException("Product is already associated with another PetProduct");
//        }
        PetProduct petProductToSave = modelMapper.map(petProductRequest, PetProduct.class);
        petProductRepository.save(petProductToSave);
        PetProductResponse petProductResponse = modelMapper.map(petProductToSave, PetProductResponse.class);
        return Optional.of(petProductResponse);
    }

    @Override
    public List<PetProductResponse> getAll(int page, int size) {
        Page<PetProduct> petProductsPage = petProductRepository.findAll(PageRequest.of(page, size));
        List<PetProduct> petProducts = petProductsPage.getContent();
        if(petProducts.isEmpty()){
            petProducts = petProductRepository.findAll();
        }
        return petProducts.stream()
                .map(petProduct -> modelMapper.map(petProduct, PetProductResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PetProductResponse> findById(Long id) {
        Optional<PetProduct> petProduct = petProductRepository.findById(id);
        if(petProduct.isPresent()){
            return Optional.of(modelMapper.map(petProduct,PetProductResponse.class));
        }else{
            throw new ResourceNotFoundException("Pet Product not found with id : " + id);
        }
    }

    @Override
    public Optional<PetProductResponse> delete(Long id) {
        Optional<PetProduct> petProduct = petProductRepository.findById(id);
        if(petProduct.isPresent()){
            petProductRepository.delete(petProduct.get());
            return Optional.of(modelMapper.map(petProduct, PetProductResponse.class));
        }else{
            throw new ResourceNotFoundException("Pet Product not found with name : " + id);
        }
    }

    @Override
    public long getTotalPages(Integer size) {
        long totalpetProducts = petProductRepository.count();
        return (totalpetProducts + size - 1) / size;
    }
}
