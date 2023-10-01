package com.example.demo.services.impl;

import com.example.demo.models.Convenient;
import com.example.demo.repositories.ConvenientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvenientServiceImpl {

    @Autowired
    private ConvenientRepository convenientRepository;

    public List<Convenient> getAll(){
        return convenientRepository.findAll();
    }
}
