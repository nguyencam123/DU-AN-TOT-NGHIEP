package com.example.demo.cors.admin.services.impl;

import com.example.demo.cors.admin.model.request.AdminPromotionRequest;
import com.example.demo.cors.admin.model.response.AdminHomestayResponse;
import com.example.demo.cors.admin.model.response.AdminPromotionResponse;
import com.example.demo.cors.admin.repository.AdminPromotionRepository;
import com.example.demo.cors.admin.services.AdminPromotionService;
import com.example.demo.cors.common.base.PageableObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdminPromotionServiceImpl implements AdminPromotionService {

    @Autowired
    private AdminPromotionRepository adminPromotionRepository;

    @Override
    public PageableObject<AdminPromotionResponse> getAll(AdminPromotionRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(),request.getSize());
        Page<AdminPromotionResponse> adminPromotionResponses= adminPromotionRepository.getAll(pageable,request);
        return new PageableObject<>(adminPromotionResponses);
    }
}
