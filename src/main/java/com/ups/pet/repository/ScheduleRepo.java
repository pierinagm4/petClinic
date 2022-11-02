package com.ups.pet.repository;

import com.ups.pet.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepo extends JpaRepository<Schedule, Long> {

    String QUERRY="SELECT * FROM SCHEDULE SCH " +
            "JOIN SCHEDULE_EMPLOYEE SCE ON (SCE.SCHEDULE_EMPLOYEE_ID= SCH.ID) " +
            "JOIN EMPLOYEES EMP ON (SCE.EMPLOYEE_ID = EMP.ID) " +
            "JOIN SCHEDULE_PET SCP ON (SCP.SCHEDULE_PET_ID= SCH.ID)  " +
            "JOIN PETS PET ON (SCP.PET_ID = PET.ID) " +
            "JOIN SCHEDULE_ACTIVITIES SCA ON (SCA.SCHEDULE_ID = SCH.ID) ";

    String QUERYEMPLOYEE  = " WHERE  EMP.ID :employeeId";

    @Query( value  = QUERRY+QUERYEMPLOYEE, nativeQuery = true )
    List<Schedule> findByEmployee(@Param("employeeId") Long employeeId);

    String QUERYPET  = " WHERE  PET.ID = :petId";

    @Query( value  = QUERRY+QUERYPET, nativeQuery = true )
    List<Schedule> findByPet(@Param("petId") Long petId);

    String QUERYCUSTOMER  =  " WHERE  PET.CUSTOMER_ID = :customerId";

    @Query( value  = QUERRY+QUERYCUSTOMER , nativeQuery = true )
    List<Schedule> findByCustomer(@Param("customerId") Long customerId);


}
