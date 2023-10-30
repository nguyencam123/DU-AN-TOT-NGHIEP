package com.example.demo.cors.homestayowner.service.impl;

import com.example.demo.cors.homestayowner.repository.HomestayOwnerPromotionRepository;
import com.example.demo.cors.homestayowner.service.HomestayOwnerPromotionService;
import com.example.demo.entities.Promotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomestayOwnerPromotionServiceImpl implements HomestayOwnerPromotionService {

    @Autowired
    private HomestayOwnerPromotionRepository homestayOwnerPromotionRepository;

    @Override
    public List<Promotion> getPromotion() {
        return homestayOwnerPromotionRepository.findAll();
    }
}
