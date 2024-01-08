package com.example.demo.cors.customer.services;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerConvenientHomestayRequest;
import com.example.demo.entities.ConvenientHomestay;

import java.util.List;

public interface CustomerConvenientHomestayService {

    PageableObject<ConvenientHomestay> getAllConvenient(CustomerConvenientHomestayRequest request);

    List<ConvenientHomestay> getAllByConvenientType(CustomerConvenientHomestayRequest request);
}
