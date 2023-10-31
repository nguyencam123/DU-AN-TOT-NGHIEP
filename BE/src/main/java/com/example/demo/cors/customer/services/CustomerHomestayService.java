package com.example.demo.cors.customer.services;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerHomestayRequest;
import com.example.demo.cors.customer.model.response.CustomerHomestayResponse;

public interface CustomerHomestayService {

    PageableObject<CustomerHomestayResponse> getListHomestay(CustomerHomestayRequest request);

    PageableObject<CustomerHomestayResponse> getHomestayByConvenientId(CustomerHomestayRequest request);

    PageableObject<CustomerHomestayResponse> getHomestayByProvince(CustomerHomestayRequest request);

    PageableObject<CustomerHomestayResponse> getHomestayByRegion(CustomerHomestayRequest request);

    CustomerHomestayResponse getHomestayById(CustomerHomestayRequest customerHomestayRequest);

}
