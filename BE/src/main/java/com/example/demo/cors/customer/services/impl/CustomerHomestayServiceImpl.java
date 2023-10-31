package com.example.demo.cors.customer.services.impl;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerHomestayRequest;
import com.example.demo.cors.customer.model.response.CustomerHomestayResponse;
import com.example.demo.cors.customer.repository.CustomerHomestayRepository;
import com.example.demo.cors.customer.services.CustomerHomestayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerHomestayServiceImpl implements CustomerHomestayService {

    @Autowired
    private CustomerHomestayRepository customerHomestayRepository;

    @Override
    public PageableObject<CustomerHomestayResponse> getListHomestay(CustomerHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<CustomerHomestayResponse> res = customerHomestayRepository.getListHomestay(pageable);
        return new PageableObject<>(res);
    }

    @Override
    public PageableObject<CustomerHomestayResponse> getHomestayByConvenientId(CustomerHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<CustomerHomestayResponse> res = customerHomestayRepository.getHomestayByConvenientId(pageable, request);
        return new PageableObject<>(res);
    }

    @Override
    public PageableObject<CustomerHomestayResponse> getHomestayByProvince(CustomerHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<CustomerHomestayResponse> res = customerHomestayRepository.getHomestayByProvince(pageable, request);
        return new PageableObject<>(res);
    }

    @Override
    public PageableObject<CustomerHomestayResponse> getHomestayByRegion(CustomerHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<CustomerHomestayResponse> res = customerHomestayRepository.getHomestayByRegion(pageable, request);
        return new PageableObject<>(res);
    }

    @Override
    public CustomerHomestayResponse getHomestayById(CustomerHomestayRequest customerHomestayRequest) {
        return customerHomestayRepository.getHomestayById(customerHomestayRequest);
    }

}
