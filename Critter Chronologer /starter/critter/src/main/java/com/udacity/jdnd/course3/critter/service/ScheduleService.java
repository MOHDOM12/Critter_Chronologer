package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private PetRepository  petRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Long createSchedule(Schedule schedule)
    {
        return scheduleRepository.save(schedule).getId();
    }

    public List<Schedule> FindScheduleByPet(Long petId){
        Pet pet = petRepository.findById(petId)
                .orElseThrow(ResourceNotFoundException::new);

        return scheduleRepository.getSchedulesByPetsContains(pet);
    }

    public List<Schedule> FindScheduleByEmployee(Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        return scheduleRepository.getSchedulesByEmployeesContains(employee);
    }

    public List<Schedule> findScheduleByCustomer(Long cId){
        Optional<Customer> customerOptional = customerRepository.findById(cId);
        if (! customerOptional.isPresent()){
            return null;
        }
        Customer customer= customerOptional.get();
        List<Schedule> scheduleList = scheduleRepository.findByPetsIn(customer.getPets());
        return scheduleList;
    }

    public List<Schedule> getAll(){
        return scheduleRepository.findAll();
    }

}
