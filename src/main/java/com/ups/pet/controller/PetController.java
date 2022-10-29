package com.ups.pet.controller;

import com.ups.pet.entity.Pet;
import com.ups.pet.entity.request.PetDTO;
import com.ups.pet.service.PetServiceImpl;
import com.ups.pet.service.exception.PetNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetServiceImpl petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = new Pet(petDTO.getId(), petDTO.getType(), petDTO.getName(), petDTO.getBirthDate(), petDTO.getNotes());
        PetDTO convertedPet;
        try {
            convertedPet = convertPetToPetDTO(petService.savePet(pet, petDTO.getOwnerId()));
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pet could not be saved", exception);
        }
        return convertedPet;
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Optional<Pet> optionalPet = petService.getPet(petId);
        Pet pet = optionalPet.orElseThrow(PetNotFoundException::new);
        return convertPetToPetDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
       List<Pet> pets =  petService.getPets();
       return convertListPetToListPetDTO(pets);
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets =  petService.getPetsByOwner(ownerId);
        return convertListPetToListPetDTO(pets);
    }

    private PetDTO convertPetToPetDTO(Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getCustomer().getId());
        return petDTO;
    }

    private List<PetDTO> convertListPetToListPetDTO(List<Pet> pets) {
        return pets.stream()
                .map(this::convertPetToPetDTO).toList();
    }


}
