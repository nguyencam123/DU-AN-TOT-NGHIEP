package com.example.demo.cors.customer.services;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerHomestayRequest;
import com.example.demo.entities.Homestay;

public interface CustomerHomestayService {

    PageableObject<Homestay> getListHomestay(CustomerHomestayRequest request);

    PageableObject<Homestay> getHomestayByConvenientId(CustomerHomestayRequest request);

    Homestay getHomestayById(CustomerHomestayRequest request);

}
