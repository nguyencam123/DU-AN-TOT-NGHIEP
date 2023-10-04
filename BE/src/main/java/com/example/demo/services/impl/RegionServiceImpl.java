package com.example.demo.services.impl;

import com.example.demo.entities.Region;
import com.example.demo.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl {
    @Autowired
    private RegionRepository regionRepository;

    public List<Region> getAll(){
        return regionRepository.findAll();
    }
}
