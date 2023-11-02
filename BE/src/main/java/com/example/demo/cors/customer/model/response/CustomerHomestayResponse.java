package com.example.demo.cors.customer.model.response;

import com.example.demo.entities.base.IsIdentified;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface CustomerHomestayResponse extends IsIdentified {

    @Value("#{target.name}")
    String getName();

    @Value("#{target.address}")
    String getAddress();

    @Value("#{target.price}")
    String getPrice();

    @Value("#{target.numberPerson}")
    String getNumberPerson();

    @Value("#{target.province}")
    String getProvince();

    @Value("#{target.region}")
    String getRegion();

    @Value("#{target.status}")
    String getStatus();

    @Value("#{target.imageUrls}")
    List<String> getImageUrls();

}
