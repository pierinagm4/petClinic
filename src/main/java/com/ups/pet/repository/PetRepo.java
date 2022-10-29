package com.ups.pet.repository;

import com.ups.pet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PetRepo extends JpaRepository<Pet, Long> {

    @Query( "SELECT p FROM Pet p WHERE p.customer.id = :ownerId")
    List<Pet> findByOwnerId(@Param("ownerId") Long ownerId);

}
