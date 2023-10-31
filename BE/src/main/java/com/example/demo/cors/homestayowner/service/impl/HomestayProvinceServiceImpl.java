package com.example.demo.cors.homestayowner.service.impl;

import com.example.demo.cors.homestayowner.repository.HomestayOwnerProvinceRepository;
import com.example.demo.cors.homestayowner.service.HomestayOwnerProvinceService;
import com.example.demo.entities.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomestayProvinceServiceImpl implements HomestayOwnerProvinceService {

    @Autowired
    private HomestayOwnerProvinceRepository provinceRepository;

    @Override
    public List<Province> getAll() {
        return provinceRepository.findAll();
    }
}
