package com.example.demo.cors.customer.services;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerPromotionRequest;
import com.example.demo.cors.customer.model.response.CustomerPromotionResponse;

public interface CustomerPromotionService {

    PageableObject<CustomerPromotionResponse> getAllPromotion( CustomerPromotionRequest request);

}
