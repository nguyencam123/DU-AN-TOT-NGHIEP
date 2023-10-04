package com.example.demo.services.impl;

import com.example.demo.entities.Sale;
import com.example.demo.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl {
    @Autowired
    private SaleRepository saleRepository;

    public List<Sale> getAll(){
        return saleRepository.findAll();
    }
}
