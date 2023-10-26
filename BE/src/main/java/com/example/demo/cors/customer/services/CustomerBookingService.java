package com.example.demo.cors.customer.services;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerBookingRequest;
import com.example.demo.cors.customer.model.response.CustomerBookingResponse;

public interface CustomerBookingService {

    PageableObject<CustomerBookingResponse> getBookingByUser(CustomerBookingRequest request);

}
