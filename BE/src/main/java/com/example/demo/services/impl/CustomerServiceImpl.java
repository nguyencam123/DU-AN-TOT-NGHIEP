package com.example.demo.services.impl;

import com.example.demo.models.Country;
import com.example.demo.models.Customer;
import com.example.demo.repositories.CountryRepository;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }
}
