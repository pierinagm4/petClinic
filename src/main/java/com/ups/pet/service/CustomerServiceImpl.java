package com.ups.pet.service;

import com.ups.pet.entity.Customer;
import com.ups.pet.entity.Pet;
import com.ups.pet.repository.CustomerRepo;
import com.ups.pet.repository.PetRepo;
import com.ups.pet.service.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private PetRepo petRepository;

    @Override
    public Customer saveCustomer(Customer customer, List<Long> petIds) {
        List<Pet> pets = new ArrayList<>();
        if(petIds != null && !petIds.isEmpty()){
            pets = petIds.stream().map( id -> petRepository.getReferenceById(id)).toList();
        }
        customer.setPets(pets);
        return customerRepo.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public Customer getOwnerByPet(Long petId) {
        return customerRepo.findByPet(petId);
    }


}
