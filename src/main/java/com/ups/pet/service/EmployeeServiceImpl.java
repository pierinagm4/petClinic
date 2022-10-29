package com.ups.pet.service;

import com.ups.pet.entity.Employee;
import com.ups.pet.entity.EmployeeSkill;
import com.ups.pet.repository.EmployeeRepo;
import com.ups.pet.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public Employee saveEmployee(Employee employee, Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable) {

        if(skills != null && !skills.isEmpty()){
            employee.setSkills(skills);
        }
        if(daysAvailable != null && !daysAvailable.isEmpty()){
            employee.setDaysAvailable(daysAvailable);
        }
        return employeeRepo.save(employee);

    }

    @Override
    public Optional<Employee> getEmployee(Long employeeId) {
        return employeeRepo.findById(employeeId);
    }

    @Override
    public List<Employee> findEmployeesForService(DayOfWeek day, Set<EmployeeSkill> skills) {
        List<String> listSkills = skills.stream().map(Enum::name).toList();
        return employeeRepo.findByDayAvailability(day.name(),listSkills);
    }
}
