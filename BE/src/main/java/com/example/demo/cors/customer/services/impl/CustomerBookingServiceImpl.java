package com.example.demo.cors.customer.services.impl;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerBookingRequest;
import com.example.demo.cors.customer.model.response.CustomerBookingResponse;
import com.example.demo.cors.customer.repository.CustomerBookingRepository;
import com.example.demo.cors.customer.services.CustomerBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerBookingServiceImpl implements CustomerBookingService {

    @Autowired
    private CustomerBookingRepository customerBookingRepository;

    @Override
    public PageableObject<CustomerBookingResponse> getBookingByUser(CustomerBookingRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<CustomerBookingResponse> res = customerBookingRepository.getBookingByUser(pageable, request);
        return new PageableObject<>(res);
    }
}
