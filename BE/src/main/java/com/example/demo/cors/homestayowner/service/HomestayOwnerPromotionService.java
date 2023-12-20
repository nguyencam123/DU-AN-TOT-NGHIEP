package com.example.demo.cors.homestayowner.service;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerPromotionRequest;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerPromotionSearchRequest;
import com.example.demo.entities.Promotion;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface HomestayOwnerPromotionService {

       List<Promotion> getPromotion(String idOwner);

       PageableObject<Promotion> searchPromotionByNameAndStatus(HomestayOwnerPromotionSearchRequest request);

       Promotion addPromotion(HomestayOwnerPromotionRequest request) throws IOException;

       Promotion updatePromotion(String idPromotion,HomestayOwnerPromotionRequest request) throws IOException;

       Promotion updatePromotionStatus(String idPromotion) throws IOException;

}
