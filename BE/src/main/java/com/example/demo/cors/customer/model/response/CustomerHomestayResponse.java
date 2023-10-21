package com.example.demo.cors.customer.model.response;

import com.example.demo.entities.base.IsIdentified;
import org.springframework.beans.factory.annotation.Value;

public interface CustomerHomestayResponse extends IsIdentified {

    @Value("#{target.stt}")
    String getStt();

    @Value("#{target.homestay_name}")
    String getHomestay_Name();

    @Value("#{target.image}")
    String getImage();

    @Value("#{target.price}")
    String getPrice();

    @Value("#{target.province_name}")
    String getProvince_Name();

}
