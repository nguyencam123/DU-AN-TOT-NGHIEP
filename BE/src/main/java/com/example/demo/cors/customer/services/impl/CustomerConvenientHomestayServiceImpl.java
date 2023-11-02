package com.example.demo.cors.customer.services.impl;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerConvenientHomestayRequest;
import com.example.demo.cors.customer.repository.CustomerConvenientHomestayRepository;
import com.example.demo.cors.customer.services.CustomerConvenientHomestayService;
import com.example.demo.entities.ConvenientHomestay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerConvenientHomestayServiceImpl implements CustomerConvenientHomestayService {

    @Autowired
    private CustomerConvenientHomestayRepository customerConvenientHomestayRepository;

    @Override
    public PageableObject<ConvenientHomestay> getAllConvenient(CustomerConvenientHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<ConvenientHomestay> res = customerConvenientHomestayRepository.findAll(pageable);
        return new PageableObject<>(res);
    }

}
