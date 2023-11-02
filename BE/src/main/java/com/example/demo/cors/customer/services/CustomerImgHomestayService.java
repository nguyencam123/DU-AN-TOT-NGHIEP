package com.example.demo.cors.customer.services;

import com.example.demo.cors.customer.model.request.CustomerImgHomestayRequest;
import com.example.demo.cors.customer.model.response.CustomerImgHomestayResponse;

import java.util.List;

public interface CustomerImgHomestayService {

    List<CustomerImgHomestayResponse> getImgHomestayByHomestayId(CustomerImgHomestayRequest request);

}
