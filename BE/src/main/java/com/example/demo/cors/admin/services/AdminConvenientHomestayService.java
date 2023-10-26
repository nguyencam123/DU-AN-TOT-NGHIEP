package com.example.demo.cors.admin.services;

import com.example.demo.cors.admin.model.request.AdminConvenientHomestayRequest;
import com.example.demo.cors.admin.model.response.AdminConvenientHomestayResponse;
import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerConvenientHomestayRequest;
import com.example.demo.cors.customer.model.response.CustomerConvenientHomestayResponse;

public interface AdminConvenientHomestayService {

    PageableObject<AdminConvenientHomestayResponse> getAllConvenient(AdminConvenientHomestayRequest request);

}
