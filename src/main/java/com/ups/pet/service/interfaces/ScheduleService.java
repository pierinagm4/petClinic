package com.ups.pet.service.interfaces;

import com.ups.pet.entity.EmployeeSkill;
import com.ups.pet.entity.Schedule;

import java.util.List;
import java.util.Set;

public interface ScheduleService {

    Schedule saveSchedule(Schedule schedule, Set<EmployeeSkill> activities, List<Long> employeeIds ,List<Long> petIds);

    List<Schedule> getAllSchedules();

    List<Schedule> getScheduleForPet(long petId);

    List<Schedule> getScheduleForEmployee(long employeeId);

    List<Schedule> getScheduleForCustomer(long customerId);

}
