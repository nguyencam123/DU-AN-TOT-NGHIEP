package com.example.demo.cors.customer.services;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerHomestayRequest;
import com.example.demo.entities.Homestay;
import com.example.demo.entities.User;

public interface CustomerHomestayService {

    PageableObject<Homestay> getListHomestay(CustomerHomestayRequest request);

    Homestay getHomestayById(CustomerHomestayRequest request);

    PageableObject<Homestay> findAllBetweenDate(CustomerHomestayRequest request);

    User getCustomerByToken(String token);

    PageableObject<Homestay> searchHomestayByPromotion(CustomerHomestayRequest request);

}
