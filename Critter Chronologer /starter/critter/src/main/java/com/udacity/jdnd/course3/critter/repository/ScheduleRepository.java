package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>
{
//    public List<Schedule> findByPetsId(Long petId);
//   public List<Schedule> findByPets_Id(Long petId);
    List<Schedule> getSchedulesByPetsContains(Pet pet);
    List<Schedule> getSchedulesByEmployeesContains(Employee employee);
    public List<Schedule> findByEmployeesId(Long employeeId);
    public List<Schedule> findByPetsIn(List<Pet> pets);
}
