package com.ups.pet.service.interfaces;

import com.ups.pet.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer saveCustomer(Customer customer, List<Long> pets);

    List<Customer> getAllCustomers();

    Customer getOwnerByPet (Long petId);

}
