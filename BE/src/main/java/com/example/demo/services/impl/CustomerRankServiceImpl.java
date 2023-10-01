package com.example.demo.services.impl;

import com.example.demo.models.Country;
import com.example.demo.models.CustomerRank;
import com.example.demo.repositories.CountryRepository;
import com.example.demo.repositories.CustomerRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerRankServiceImpl {

    @Autowired
    private CustomerRankRepository customerRankRepository;

    public List<CustomerRank> getAll(){
        return customerRankRepository.findAll();
    }
}
