package com.example.demo.services.impl;

import com.example.demo.entities.District;
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
