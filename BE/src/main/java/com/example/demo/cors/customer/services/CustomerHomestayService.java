package com.example.demo.cors.customer.services;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerHomestayRequest;
import com.example.demo.cors.customer.model.response.CustomerHomestayResponse;

import java.util.List;

public interface CustomerHomestayService {

    PageableObject<CustomerHomestayResponse> getListHomestay(CustomerHomestayRequest request);

}
