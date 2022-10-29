package com.ups.pet.repository;

import com.ups.pet.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    String QUERYFIND ="SELECT * FROM EMPLOYEES  EMP " +
            "JOIN EMPLOYEE_DAYS_AVAILABLE EDA ON (EDA.EMPLOYEE_ID = EMP.ID) " +
            "JOIN EMPLOYEE_SKILLS SKI ON (SKI.EMPLOYEE_ID = EMP.ID)  " +
            "WHERE EDA.DAYS_AVAILABLE = :day " +
            "AND SKI.SKILLS IN (:skills)";

    @Query(value  = QUERYFIND, nativeQuery = true)
    List<Employee> findByDayAvailability(@Param("day") String day, @Param("skills") List<String> skills);

}
