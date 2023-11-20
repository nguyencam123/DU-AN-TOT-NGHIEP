package com.example.demo.cors.customer.services.impl;

import com.example.demo.cors.customer.model.request.CustomerImgHomestayRequest;
import com.example.demo.cors.customer.repository.CustomerImgHomestayRepository;
import com.example.demo.cors.customer.services.CustomerImgHomestayService;
import com.example.demo.entities.ImgHomestay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerImgHomestayServiceImpl implements CustomerImgHomestayService {

    @Autowired
    private CustomerImgHomestayRepository customerImgHomestayRepository;

    @Override
    public List<ImgHomestay> getImgHomestayByHomestayId(CustomerImgHomestayRequest request) {
        return customerImgHomestayRepository.getImgHomestayByIdHomestay(request);
    }

}
