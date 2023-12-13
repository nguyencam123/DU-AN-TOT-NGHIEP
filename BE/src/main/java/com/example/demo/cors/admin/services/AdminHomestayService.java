package com.example.demo.cors.admin.services;

import com.example.demo.cors.admin.model.request.AdminHomestayRequest;
import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.entities.Admin;
import com.example.demo.entities.Homestay;

public interface AdminHomestayService {

    PageableObject<Homestay> getAllHomestay(AdminHomestayRequest request);

    Admin getAdminByToken(String token);
}
