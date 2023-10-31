package com.example.demo.cors.customer.services.impl;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerPromotionRequest;
import com.example.demo.cors.customer.model.response.CustomerConvenientHomestayResponse;
import com.example.demo.cors.customer.model.response.CustomerPromotionResponse;
import com.example.demo.cors.customer.repository.CustomerPromotionRepository;
import com.example.demo.cors.customer.services.CustomerPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerPromotionServiceImpl implements CustomerPromotionService {

    @Autowired
    private CustomerPromotionRepository customerPromotionRepository;

    @Override
    public PageableObject<CustomerPromotionResponse> getAllPromotion( CustomerPromotionRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<CustomerPromotionResponse> res = customerPromotionRepository.getAllPromotion(pageable);
        return new PageableObject<>(res);
    }

}
