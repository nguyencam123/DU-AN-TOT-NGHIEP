package com.example.demo.services.impl;

import com.example.demo.models.Country;
import com.example.demo.models.ScenicSpot;
import com.example.demo.repositories.CountryRepository;
import com.example.demo.repositories.ScenicSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenicSpotServiceImpl {
    @Autowired
    private ScenicSpotRepository scenicSpotRepository;

    public List<ScenicSpot> getAll(){
        return scenicSpotRepository.findAll();
    }
}
