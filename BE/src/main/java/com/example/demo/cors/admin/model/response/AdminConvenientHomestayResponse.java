package com.example.demo.cors.admin.model.response;

import org.springframework.beans.factory.annotation.Value;

public interface AdminConvenientHomestayResponse {

    @Value("#{target.convenient_name}")
    String getConvenient_Name();

    @Value("#{target.convenient_type_name}")
    String getConvenient_Type_Name();

}
