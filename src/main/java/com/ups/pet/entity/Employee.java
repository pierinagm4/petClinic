package com.ups.pet.entity;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Nationalized
    private String name;

    @ElementCollection(targetClass = EmployeeSkill.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    Set<EmployeeSkill> skills;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    Set<DayOfWeek> daysAvailable;


    public Employee() {
    }

    public Employee(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable) {
        this.name = name;
        this.skills = skills;
        this.daysAvailable = daysAvailable;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

}
