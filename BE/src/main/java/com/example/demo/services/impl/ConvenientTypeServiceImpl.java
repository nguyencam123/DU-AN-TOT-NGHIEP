package com.example.demo.services.impl;

import com.example.demo.models.ConvenientType;
import com.example.demo.repositories.ConvenientTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvenientTypeServiceImpl {

    @Autowired
    private ConvenientTypeRepository convenientTypeRepository;

    public List<ConvenientType> getAll(){
        return  convenientTypeRepository.findAll();
    }
}
