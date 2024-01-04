package com.example.demo.cors.homestayowner.service.impl;

import com.example.demo.cors.homestayowner.repository.HomestayOwnerPromotionRepository;
import com.example.demo.entities.Promotion;
import com.example.demo.infrastructure.contant.StatusPromotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class HomestayOwnerPromotionStatusService {

//    @Autowired
//    private HomestayOwnerPromotionRepository homestayOwnerPromotionRepository;
//
//    @Scheduled(fixedRate = 60000)
//    public void updatePromotionStatus() {
//        List<Promotion> promotions = homestayOwnerPromotionRepository.findByEndDateLessThanAndStatusPromotion();
//        for (Promotion promotion : promotions) {
//            promotion.setStatusPromotion(StatusPromotion.KET_THUC);
//            homestayOwnerPromotionRepository.save(promotion);
//        }
//    }

}
