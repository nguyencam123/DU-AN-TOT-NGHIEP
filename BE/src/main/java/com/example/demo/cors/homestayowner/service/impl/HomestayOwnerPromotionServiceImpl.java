package com.example.demo.cors.homestayowner.service.impl;

import com.example.demo.cors.homestayowner.model.request.HomestayOwnerPromotionRequest;
import com.example.demo.cors.homestayowner.repository.HomestayOwnerPromotionRepository;
import com.example.demo.cors.homestayowner.service.HomestayOwnerPromotionService;
import com.example.demo.entities.Promotion;
import com.example.demo.infrastructure.exception.rest.RestApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class HomestayOwnerPromotionServiceImpl implements HomestayOwnerPromotionService {

    @Autowired
    private HomestayOwnerPromotionRepository homestayOwnerPromotionRepository;

    @Override
    public List<Promotion> getPromotion() {
        return homestayOwnerPromotionRepository.findAll();
    }

    @Override
    public Promotion addPromotion(HomestayOwnerPromotionRequest request) throws IOException {
        if (isNullOrEmpty(request.getName())) {
            throw new RestApiException("Name cannot be empty");
        }

        if (request.getStartDate() <= 0 || request.getEndDate() <= 0) {
            throw new RestApiException("Start and End Dates must be greater than 0");
        }

        if (request.getEndDate() < request.getStartDate()) {
            throw new RestApiException("End Date must be greater than Start Date");
        }

        if (request.getValue() <= 0) {
            throw new RestApiException("Value cannot be empty");
        }

        if (homestayOwnerPromotionRepository.existsByName(request.getName())) {
            throw new RestApiException("Name is already in use");
        }

        Promotion promotion=new Promotion();
        promotion.setName(request.getName());
        promotion.setStartDate(request.getStartDate());
        promotion.setEndDate(request.getEndDate());
        promotion.setType(request.getType());
        promotion.setValue(request.getValue());
        Promotion promotion1=homestayOwnerPromotionRepository.save(promotion);
        return promotion1;
    }

    @Override
    public Promotion updatePromotion(String idPromotion,HomestayOwnerPromotionRequest request) throws IOException{
        if (isNullOrEmpty(request.getName())) {
            throw new RestApiException("Name cannot be empty");
        }

        if (request.getStartDate() <= 0 || request.getEndDate() <= 0) {
            throw new RestApiException("Start and End Dates must be greater than 0");
        }

        if (request.getEndDate() <= request.getStartDate()) {
            throw new RestApiException("End Date must be greater than Start Date");
        }

        if (request.getValue() <= 0) {
            throw new RestApiException("Value cannot be empty");
        }

        Promotion promotion= homestayOwnerPromotionRepository.findById(idPromotion).orElseThrow();

        if (homestayOwnerPromotionRepository.existsByName(request.getName()) && !promotion.getName().equals(request.getName())) {
            throw new RestApiException("Name is already in use");
        }

        promotion.setName(request.getName());
        promotion.setStartDate(request.getStartDate());
        promotion.setEndDate(request.getEndDate());
        promotion.setType(request.getType());
        promotion.setValue(request.getValue());
        Promotion promotion1=homestayOwnerPromotionRepository.save(promotion);
        return promotion1;
    }


    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }


}
