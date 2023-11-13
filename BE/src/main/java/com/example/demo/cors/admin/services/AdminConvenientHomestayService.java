package com.example.demo.cors.admin.services;

import com.example.demo.cors.admin.model.request.AdminConvenientHomestayRequest;
import com.example.demo.cors.admin.model.request.AdminConvenientHomestayTypeRequest;
import com.example.demo.cors.admin.model.response.AdminConvenientHomestayResponse;
import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.entities.ConvenientHomestay;
import com.example.demo.entities.ConvenientHomestayType;

import java.io.IOException;

public interface AdminConvenientHomestayService {

    PageableObject<ConvenientHomestay> getAllConvenient(AdminConvenientHomestayRequest request);

    PageableObject<ConvenientHomestayType> getAllConvenientType(AdminConvenientHomestayTypeRequest request);

    ConvenientHomestay addConvenientHomestay( AdminConvenientHomestayRequest adminConvenientHomestayRequest);

    ConvenientHomestayType addConvenientHomestayType(AdminConvenientHomestayTypeRequest adminConvenientHomestayTypeRequest);

    ConvenientHomestay updateConvenientHomestay( AdminConvenientHomestayRequest adminConvenientHomestayRequest);

    ConvenientHomestayType updateConvenientHomestayType(AdminConvenientHomestayTypeRequest adminConvenientHomestayTypeRequest);
}
