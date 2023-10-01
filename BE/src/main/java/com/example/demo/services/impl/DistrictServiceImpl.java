package com.example.demo.services.impl;

import com.example.demo.models.Country;
import com.example.demo.models.District;
import com.example.demo.repositories.CountryRepository;
import com.example.demo.repositories.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl {

    @Autowired
    private DistrictRepository districtRepository;

    public List<District> getAll(){
        return districtRepository.findAll();
    }
}
