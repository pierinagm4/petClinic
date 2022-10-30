package com.ups.pet.controller;

import com.ups.pet.entity.Employee;
import com.ups.pet.entity.Pet;
import com.ups.pet.entity.Schedule;
import com.ups.pet.entity.request.ScheduleDTO;
import com.ups.pet.service.ScheduleServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleServiceImpl scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule(scheduleDTO.getDate());
        ScheduleDTO convertedSchedule;
        try {
            convertedSchedule = convertScheduleToScheduleDTO(scheduleService.saveSchedule(schedule,scheduleDTO.getActivities(),
                    scheduleDTO.getEmployeeIds(),scheduleDTO.getPetIds()));
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee could not be saved", exception);
        }
        return convertedSchedule;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedule =  scheduleService.getAllSchedules();
        return convertListScheduleToListScheduleDTO(schedule);
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        throw new UnsupportedOperationException();
    }

    private ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        scheduleDTO.setPetIds(schedule.getPet().stream().map(Pet::getId).toList());
        scheduleDTO.setActivities(schedule.getActivities());
        scheduleDTO.setEmployeeIds(schedule.getEmployee().stream().map(Employee::getId).toList());
        return scheduleDTO;
    }

    private List<ScheduleDTO> convertListScheduleToListScheduleDTO(List<Schedule> schedule) {
        return schedule.stream()
                .map(this::convertScheduleToScheduleDTO).toList();
    }

}
