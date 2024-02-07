package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    public Long create(Employee employee)
    {
        return employeeRepository.save(employee).getId();
    }

    public Employee addEmployeeSchedule(Set<DayOfWeek> dayOfWeekSet, Long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (!employeeOptional.isPresent()) {
            return null;
        }
        Employee employee = employeeOptional.get();
        employee.setDaysAvailable(dayOfWeekSet);
        employeeRepository.save(employee);
        return employee;
    }

    public List<Employee> checkAvailability(Set<EmployeeSkill> activities, DayOfWeek date){
        List<Employee> employees = employeeRepository.getAllByDaysAvailableContains(date);
        List<Employee> employeeAvailable = new ArrayList<>();
        for (Employee employee: employees){
            if (employee.getSkills().containsAll(activities)){
                employeeAvailable.add(employee);
            }
        }
        return employeeAvailable;
    }

    public Employee getById(Long id){
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (! employeeOptional.isPresent()){
            return null;
        }
        return employeeOptional.get();
    }
}
