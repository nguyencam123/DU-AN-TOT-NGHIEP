package com.example.demo.cors.homestayowner.service.impl;

import com.example.demo.cors.homestayowner.repository.HomestayOwnerRegionRepository;
import com.example.demo.cors.homestayowner.service.HomestayOwnerRegionService;
import com.example.demo.entities.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomestayOwnerRegionServiceImpl implements HomestayOwnerRegionService {

    @Autowired
    private HomestayOwnerRegionRepository regionRepository;

    @Override
    public List<Region> getRegion() {
        return regionRepository.findAll();
    }
}
