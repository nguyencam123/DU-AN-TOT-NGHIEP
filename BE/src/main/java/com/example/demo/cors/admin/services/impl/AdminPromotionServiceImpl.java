package com.example.demo.cors.admin.services.impl;

import com.example.demo.cors.admin.model.request.AdminPromotionRequest;
import com.example.demo.cors.admin.repository.AdminPromotionRepository;
import com.example.demo.cors.admin.services.AdminPromotionService;
import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.entities.Promotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class AdminPromotionServiceImpl implements AdminPromotionService {

    @Autowired
    private AdminPromotionRepository adminPromotionRepository;

    @Override
    public PageableObject<Promotion> getAll(AdminPromotionRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(),request.getSize());
        Page<Promotion> adminPromotionResponses= adminPromotionRepository.findAll(pageable);
        return new PageableObject<>(adminPromotionResponses);
    }

    @Override
    public Promotion update() {
        long millis=System.currentTimeMillis();
        Date date=new Date(millis);
        List<Promotion> listPromotion = adminPromotionRepository.findAll();
        for (Promotion promotion : listPromotion){
              if ( Long.valueOf(String.valueOf(date)) == promotion.getStartDate()){
                  System.out.println(true);
              }

        }
        System.out.println(date);
        return null;
    }
}
