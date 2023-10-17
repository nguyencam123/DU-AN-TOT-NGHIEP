package com.example.demo.cors.admin.model.reponse;

import org.springframework.beans.factory.annotation.Value;

public interface AdminLoginReponsi {
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
