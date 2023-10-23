package com.example.demo.cors.admin.model.response;

import org.springframework.beans.factory.annotation.Value;

public interface AdminLoginResponse {

    @Value("#{target.status}")
    Integer getStatus();

    @Value("#{target.code}")
    String getCode();

    @Value("#{target.name}")
    String getName();

    @Value("#{target.password}")
    String getPass();

    @Value("#{target.username}")
    String getUsername();


}
