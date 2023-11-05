package com.example.demo.cors.customer.services.impl;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerHomestayRequest;
import com.example.demo.cors.customer.repository.CustomerHomestayRepository;
import com.example.demo.cors.customer.services.CustomerHomestayService;
import com.example.demo.entities.Homestay;
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
    public PageableObject<Homestay> getListHomestay(CustomerHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Homestay> res = customerHomestayRepository.findAll(pageable);
        return new PageableObject<>(res);
    }

    @Override
    public PageableObject<Homestay> getHomestayByConvenientId(CustomerHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Homestay> res = customerHomestayRepository.getHomestayByConvenientId(pageable, request);
        return new PageableObject<>(res);
    }

    @Override
    public Homestay getHomestayById(CustomerHomestayRequest request) {
        return customerHomestayRepository.findHomestayById(request.getHomestayId());
    }

}
