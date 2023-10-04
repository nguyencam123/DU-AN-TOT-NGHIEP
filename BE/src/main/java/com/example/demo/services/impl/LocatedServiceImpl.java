package com.example.demo.services.impl;

import com.example.demo.entities.Located;
import com.example.demo.repositories.LocatedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocatedServiceImpl {
    @Autowired
    private LocatedRepository locatedRepository;

    public List<Located> getAll(){
        return locatedRepository.findAll();
    }
}
