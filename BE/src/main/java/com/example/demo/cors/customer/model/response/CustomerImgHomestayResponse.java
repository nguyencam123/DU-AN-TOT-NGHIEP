package com.example.demo.cors.customer.model.response;

import org.springframework.beans.factory.annotation.Value;

public interface CustomerImgHomestayResponse {

    @Value("#{target.imgUrl}")
    String getImgUrl();

}
