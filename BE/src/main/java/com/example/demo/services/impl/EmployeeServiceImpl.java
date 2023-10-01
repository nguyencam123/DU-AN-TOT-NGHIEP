package com.example.demo.services.impl;

import com.example.demo.models.Country;
import com.example.demo.models.Employee;
import com.example.demo.repositories.CountryRepository;
import com.example.demo.repositories.EmployeeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl {

    @Autowired
    private EmployeeRespository employeeRespository;

    public List<Employee> getAll(){
        return employeeRespository.findAll();
    }
}
