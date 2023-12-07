package com.example.demo.cors.homestayowner.service;

import com.example.demo.cors.homestayowner.model.request.HomestayOwnerPromotionRequest;
import com.example.demo.entities.Promotion;

import java.io.IOException;
import java.util.List;

public interface HomestayOwnerPromotionService {

       List<Promotion> getPromotion(String idOwner);

       Promotion addPromotion(HomestayOwnerPromotionRequest request) throws IOException;

       Promotion updatePromotion(String idPromotion,HomestayOwnerPromotionRequest request) throws IOException;

       Promotion updatePromotionStatus(String idPromotion) throws IOException;

}
