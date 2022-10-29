package com.ups.pet.service.interfaces;

import com.ups.pet.entity.Pet;

import java.util.List;
import java.util.Optional;

public interface PetService {

    Pet savePet(Pet pet, Long customerId);

    Optional<Pet> getPet (Long petId);

    List<Pet> getPets();

    List<Pet> getPetsByOwner(Long ownerId);

}
