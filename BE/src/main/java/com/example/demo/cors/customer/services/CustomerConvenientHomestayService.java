package com.example.demo.cors.customer.services;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerConvenientHomestayRequest;
import com.example.demo.cors.customer.model.response.CustomerConvenientHomestayResponse;

public interface CustomerConvenientHomestayService {

    PageableObject<CustomerConvenientHomestayResponse> getAllConvenient(CustomerConvenientHomestayRequest request);

}
