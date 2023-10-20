package com.example.demo.cors.admin.services;

import com.example.demo.cors.admin.model.response.AdminHomestayResponse;
import com.example.demo.cors.admin.model.request.AdminHomestayRequest;
import com.example.demo.cors.common.base.PageableObject;

public interface AdminHomestayService {

    PageableObject<AdminHomestayResponse> getAll(AdminHomestayRequest request);

}
