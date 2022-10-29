package com.ups.pet.controller;

import com.ups.pet.entity.Customer;
import com.ups.pet.entity.Employee;
import com.ups.pet.entity.EmployeeSkill;
import com.ups.pet.entity.Pet;
import com.ups.pet.entity.request.CustomerDTO;
import com.ups.pet.entity.request.EmployeeDTO;
import com.ups.pet.entity.request.EmployeeRequestDTO;
import com.ups.pet.service.CustomerServiceImpl;
import com.ups.pet.service.EmployeeServiceImpl;
import com.ups.pet.service.exception.EmployeeNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.DayOfWeek;
import java.util.*;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private EmployeeServiceImpl employeeService;


    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){

        Customer customer = new Customer(customerDTO.getName(),customerDTO.getPhoneNumber(),customerDTO.getNotes());
        List<Long> petsIds = customerDTO.getPetIds();
        CustomerDTO convertedCustomer;
        try {
            convertedCustomer = convertCustomerToCustomerDTO(customerService.saveCustomer(customer, petsIds));
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User could not be saved", exception);
        }
        return convertedCustomer;

    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers =  customerService.getAllCustomers();
        return convertListCustomerToListCustomerDTO(customers);
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Customer customer = customerService.getOwnerByPet(petId);
        return convertCustomerToCustomerDTO(customer);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee(employeeDTO.getName());
        EmployeeDTO convertedEmployee;
        try {
            convertedEmployee = convertEmployeeToEmployeeDTO(employeeService.saveEmployee(employee, employeeDTO.getSkills(), employeeDTO.getDaysAvailable()));
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee could not be saved", exception);
        }
        return convertedEmployee;
    }

    @GetMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Optional<Employee> optionalEmployee = employeeService.getEmployee(employeeId);
        Employee employee = optionalEmployee.orElseThrow(EmployeeNotFoundException::new);
        return convertEmployeeToEmployeeDTO(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        Optional<Employee> optionalEmployee = employeeService.getEmployee(employeeId);
        Employee employee = optionalEmployee.orElseThrow(EmployeeNotFoundException::new);
        List<EmployeeSkill> skillList = new ArrayList<>(employee.getSkills()) ;
        Set<EmployeeSkill> skillSet = new HashSet<>(skillList);
        try{
            employeeService.saveEmployee(employee,skillSet,daysAvailable);
        }catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location could not be saved", ex);
        }

    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        DayOfWeek dayOfWeek = employeeDTO.getDate().getDayOfWeek();
        return convertListEmployeeToListEmployeeDTO(employeeService.findEmployeesForService(dayOfWeek, employeeDTO.getSkills()));
    }

    private CustomerDTO convertCustomerToCustomerDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        List<Long> petsIds = customer.getPets().stream().map(Pet::getId).toList();
        BeanUtils.copyProperties(customer, customerDTO);
        customerDTO.setPetIds(petsIds);
        return customerDTO;
    }

    private List<CustomerDTO> convertListCustomerToListCustomerDTO(List<Customer> customer) {
        return customer.stream()
                .map(this::convertCustomerToCustomerDTO).toList();
    }

    private EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        employeeDTO.setSkills(employee.getSkills());
        employeeDTO.setDaysAvailable(employee.getDaysAvailable());

        return employeeDTO;
    }

    private List<EmployeeDTO> convertListEmployeeToListEmployeeDTO(List<Employee> employee) {
        return employee.stream()
                .map(this::convertEmployeeToEmployeeDTO).toList();
    }



}
