package com.example.demo.cors.admin.services;

import com.example.demo.cors.admin.model.request.AdminPromotionRequest;
import com.example.demo.cors.admin.model.response.AdminPromotionResponse;
import com.example.demo.cors.common.base.PageableObject;

public interface AdminPromotionService {

    PageableObject<AdminPromotionResponse> getAll(AdminPromotionRequest request);

}
