package com.example.demo.cors.admin.services;

import com.example.demo.cors.admin.model.request.AdminPromotionRequest;
import com.example.demo.cors.admin.model.response.AdminPromotionResponse;
import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.entities.Promotion;

public interface AdminPromotionService {

    PageableObject<Promotion> getAll(AdminPromotionRequest request);

    Promotion update();
}
