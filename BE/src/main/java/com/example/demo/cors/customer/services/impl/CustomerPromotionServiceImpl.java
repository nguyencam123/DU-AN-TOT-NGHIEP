package com.example.demo.cors.customer.services.impl;

import com.example.demo.cors.customer.repository.CustomerPromotionRepository;
import com.example.demo.cors.customer.services.CustomerPromotionService;
import com.example.demo.entities.Promotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerPromotionServiceImpl implements CustomerPromotionService {

    @Autowired
    private CustomerPromotionRepository customerPromotionRepository;

    @Override
    public List<Promotion> getAll() {
        return customerPromotionRepository.findAll();
    }

}
