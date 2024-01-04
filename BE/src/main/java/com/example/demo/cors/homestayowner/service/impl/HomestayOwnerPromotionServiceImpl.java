package com.example.demo.cors.homestayowner.service.impl;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerPromotionRequest;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerPromotionSearchRequest;
import com.example.demo.cors.homestayowner.repository.HomestayOwnerHomestayRepository;
import com.example.demo.cors.homestayowner.repository.HomestayOwnerOwnerHomestayRepository;
import com.example.demo.cors.homestayowner.repository.HomestayOwnerPromotionRepository;
import com.example.demo.cors.homestayowner.service.HomestayOwnerPromotionService;
import com.example.demo.entities.Booking;
import com.example.demo.entities.Homestay;
import com.example.demo.entities.Promotion;
import com.example.demo.infrastructure.contant.StatusPromotion;
import com.example.demo.infrastructure.exception.rest.RestApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomestayOwnerPromotionServiceImpl implements HomestayOwnerPromotionService {

    @Autowired
    private HomestayOwnerPromotionRepository homestayOwnerPromotionRepository;

    @Autowired
    private HomestayOwnerHomestayRepository homestayOwnerHomestayRepository;

    @Autowired
    private HomestayOwnerOwnerHomestayRepository homestayOwnerOwnerHomestayRepository;

    @Override
    public List<Promotion> getPromotion(String idOwner) {
        return homestayOwnerPromotionRepository.getAllPromotion(idOwner);
    }

    @Override
    public PageableObject<Promotion> searchPromotionByNameAndStatus(HomestayOwnerPromotionSearchRequest request) {
        List<Promotion> promotions=homestayOwnerPromotionRepository.findByEndDateLessThanAndStatusPromotion();
        if (promotions!=null){
        for (Promotion promotion: promotions){
            promotion.setStatusPromotion(StatusPromotion.KET_THUC);
        }
        }
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Promotion> getPromotion = homestayOwnerPromotionRepository.getBookingByNameAndStatus(request, pageable);
        return new PageableObject<>(getPromotion);
    }

    @Override
    public Promotion addPromotion(HomestayOwnerPromotionRequest request) throws IOException {
        if (isNullOrEmpty(request.getName())) {
            throw new RestApiException("Tên không được để trống");
        }

        if (request.getStartDate() <= 0 || request.getEndDate() <= 0) {
            throw new RestApiException("Ngày bắt đầu và ngày kết thúc không được nhỏ hơn 0");
        }

        if (request.getEndDate() < request.getStartDate()) {
            throw new RestApiException("Ngày kết thúc không được nhỏ hơn ngày bắt đầu");
        }

        if (request.getValue() <= 0) {
            throw new RestApiException("Value không được để trống");
        }

        if (homestayOwnerPromotionRepository.existsByName(request.getName())) {
            throw new RestApiException("tên không được trùng");
        }

        Promotion promotion=new Promotion();
        promotion.setName(request.getName());
        promotion.setStartDate(request.getStartDate());
        promotion.setEndDate(request.getEndDate());
        promotion.setType(request.getType());
        promotion.setValue(request.getValue());
        promotion.setIdOwnerHomestay(homestayOwnerOwnerHomestayRepository.findById(request.getOwner()).get());
        promotion.setStatusPromotion(StatusPromotion.HOAT_DONG);
        Promotion promotion1=homestayOwnerPromotionRepository.save(promotion);
        for (String homestay: request.getHomestay()){
            Homestay homestay1=homestayOwnerHomestayRepository.findById(homestay).orElse(null);
            homestay1.setPromotion(promotion1);
            homestayOwnerHomestayRepository.save(homestay1);
        }
        return promotion1;
    }



    @Override
    public Promotion updatePromotion(String idPromotion,HomestayOwnerPromotionRequest request) throws IOException{
        if (isNullOrEmpty(request.getName())) {
            throw new RestApiException("Tên không được để trống");
        }

        if (request.getStartDate() <= 0 || request.getEndDate() <= 0) {
            throw new RestApiException("Ngày bắt đầu và nagyf kết thúc không được nhỏ hơn 0");
        }

        if (request.getEndDate() <= request.getStartDate()) {
            throw new RestApiException("Ngày kết thúc không được nhỏ hơn ngày bắt đầu");
        }

        if (request.getValue() <= 0) {
            throw new RestApiException("Value không được để trống");
        }

        Promotion promotion= homestayOwnerPromotionRepository.findById(idPromotion).orElseThrow();

        if (homestayOwnerPromotionRepository.existsByName(request.getName()) && !promotion.getName().equals(request.getName())) {
            throw new RestApiException("Tên đã được sử dụng");
        }

        promotion.setName(request.getName());
        promotion.setStartDate(request.getStartDate());
        promotion.setEndDate(request.getEndDate());
        promotion.setType(request.getType());
        promotion.setValue(request.getValue());
        Promotion promotion1=homestayOwnerPromotionRepository.save(promotion);
        for (String homestay: request.getHomestay()){
            Homestay homestay1=homestayOwnerHomestayRepository.findById(homestay).orElse(null);
            homestay1.setPromotion(promotion1);
            homestayOwnerHomestayRepository.save(homestay1);
        }
        return promotion1;
    }

    @Override
    public Promotion updatePromotionStatus(String idPromotion) throws IOException {
        Promotion promotion= homestayOwnerPromotionRepository.findById(idPromotion).orElseThrow();
        promotion.setStatusPromotion(StatusPromotion.KET_THUC);
        Promotion promotion1=homestayOwnerPromotionRepository.save(promotion);
        return promotion1;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }


}
