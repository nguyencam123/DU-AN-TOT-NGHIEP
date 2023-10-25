package com.example.demo.cors.homestayowner.model.reponse;

import org.springframework.beans.factory.annotation.Value;

public interface HomestayOwnerHomestayReponse {

    @Value("#{target.name}")
    String getName();

    @Value("#{target.address}")
    String getAddress();

    @Value("#{target.price}")
    String getPrice();

    @Value("#{target.startDate}")
    String getStartDate();

    @Value("#{target.numberPerson}")
    String getNumberPerson();

    @Value("#{target.province}")
    String getProvince();

    @Value("#{target.region}")
    String getRegion();

    @Value("#{target.status}")
    String getStatus();

}
