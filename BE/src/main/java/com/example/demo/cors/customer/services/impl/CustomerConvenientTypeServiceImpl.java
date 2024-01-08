package com.example.demo.cors.customer.services.impl;

import com.example.demo.cors.customer.repository.CustomerConvenientTypeRepository;
import com.example.demo.cors.customer.services.CustomerConvenientTypeService;
import com.example.demo.entities.ConvenientHomestayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerConvenientTypeServiceImpl implements CustomerConvenientTypeService {

    @Autowired
    private CustomerConvenientTypeRepository customerConvenientTypeRepository;

    @Override
    public List<ConvenientHomestayType> getAll() {
        return customerConvenientTypeRepository.getAll();
    }
}
