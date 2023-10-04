package com.example.demo.services.impl;

import com.example.demo.entities.ScenicSpot;
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
