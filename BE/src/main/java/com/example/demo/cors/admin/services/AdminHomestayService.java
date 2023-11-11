package com.example.demo.cors.admin.services;

import com.example.demo.cors.admin.model.request.AdminApprovalRequest;
import com.example.demo.cors.admin.model.request.AdminChangeStatusHomestayRequest;
import com.example.demo.cors.admin.model.response.AdminHomestayResponse;
import com.example.demo.cors.admin.model.request.AdminHomestayRequest;
import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.entities.Homestay;

public interface AdminHomestayService {

    PageableObject<Homestay> getAllChoDuyet(AdminHomestayRequest request);

    PageableObject<Homestay> getAllDaDuyet(AdminHomestayRequest request);

    PageableObject<Homestay> getAllDaXoa(AdminHomestayRequest request);

    PageableObject<Homestay> getAllKhongDuyet(AdminHomestayRequest request);

    PageableObject<Homestay> getAllHomestay(AdminHomestayRequest request);

    PageableObject<Homestay> getAllById(AdminHomestayRequest request);

    PageableObject<Homestay> findByNameChoDuyet(AdminHomestayRequest request);

    PageableObject<Homestay> findByNameDaDuyet(AdminHomestayRequest request);
}
