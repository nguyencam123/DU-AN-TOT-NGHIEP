package com.example.demo.cors.customer.services;

import com.example.demo.cors.customer.model.request.CustomerImgHomestayRequest;
import com.example.demo.entities.ImgHomestay;

import java.util.List;

public interface CustomerImgHomestayService {

    List<ImgHomestay> getImgHomestayByHomestayId(CustomerImgHomestayRequest request);

}
