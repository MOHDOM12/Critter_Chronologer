package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Long create(Customer customer)
    {
        return  customerRepository.save(customer).getId();
    }

    public List<Customer> getAllCustomer()
    {
        return customerRepository.findAll();
    }

    public Customer getOwnerByPet(Long petId)
    {
        return customerRepository.findByPetsId(petId);
    }

    public Customer findCustomerById(Long customerId)
    {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if(! customerOptional.isPresent())
        {
            return null;
        }
        return customerOptional.get();
    }
}
