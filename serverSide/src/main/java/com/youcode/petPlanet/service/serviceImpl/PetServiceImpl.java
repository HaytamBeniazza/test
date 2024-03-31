package com.youcode.petPlanet.service.serviceImpl;

import com.youcode.petPlanet.dto.dtoRequest.PetRequest;
import com.youcode.petPlanet.dto.dtoResponse.PetResponse;
import com.youcode.petPlanet.entity.Pet;
import com.youcode.petPlanet.exception.RegisterUserException;
import com.youcode.petPlanet.exception.ResourceNotFoundException;
import com.youcode.petPlanet.repository.PetRepository;
import com.youcode.petPlanet.service.serviceInterface.PetService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService {

    private final ModelMapper modelMapper;
    private final PetRepository petRepository;


    public PetServiceImpl(ModelMapper modelMapper, PetRepository petRepository){
        this.modelMapper = modelMapper;
        this.petRepository = petRepository;
    }
    @Override
    public Optional<PetResponse> Add(PetRequest pet) {
        Optional<Pet> checkPet = petRepository.findByName(pet.getName());
        if(checkPet.isEmpty()){
            Pet petToSave = modelMapper.map(pet , Pet.class);
            petRepository.save(petToSave);
            return Optional.of(modelMapper.map(pet, PetResponse.class));
        }else{
            throw new RegisterUserException("Name already exists");
        }
    }

    @Override
    public Optional<PetResponse> findByName(String name) {

        Optional<Pet> pet = petRepository.findByName(name);
        if(pet.isPresent()){
            return Optional.of(modelMapper.map(pet,PetResponse.class));
        }else{
            throw new ResourceNotFoundException("Pet not found with name : " + name);
        }
    }

    @Override
    public Optional<PetResponse> findById(Long id) {
        Optional<Pet> pet = petRepository.findById(id);
        if(pet.isPresent()){
            return Optional.of(modelMapper.map(pet,PetResponse.class));
        }else{
            throw new ResourceNotFoundException("Pet not found with ID : " + id);
        }
    }

    @Override
    public List<PetResponse> getAll(int page, int size) {
        Page<Pet> petsPage = petRepository.findAll(PageRequest.of(page, size));
        List<Pet> pets = petsPage.getContent();
        if(pets.isEmpty()){
            pets = petRepository.findAll();
        }
        return pets.stream()
                .map(pet -> modelMapper.map(pet, PetResponse.class))
                .collect(Collectors.toList());
    }



    @Override
    public Optional<PetResponse> update(Long id, PetRequest pet) {
        Optional<Pet> petToUpdate = petRepository.findById(id);
        if(petToUpdate.isPresent()){
            petToUpdate.get().setName(pet.getName());
            petToUpdate.get().setDescription(pet.getDescription());
            petRepository.save(petToUpdate.get());
            return Optional.ofNullable(modelMapper.map(petToUpdate, PetResponse.class));
        }else{
            throw new ResourceNotFoundException("Pet not found with name : " + id);
        }
    }

    @Override
    public Optional<PetResponse> delete(Long id) {
        Optional<Pet> pet = petRepository.findById(id);
        if(pet.isPresent()){
            petRepository.delete(pet.get());
            return Optional.of(modelMapper.map(pet, PetResponse.class));
        }else{
            throw new ResourceNotFoundException("Pet not found with name : " + id);
        }
    }
}
