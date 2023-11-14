package com.example.demo.cors.customer.model.response;

import com.example.demo.entities.base.IsIdentified;
import org.springframework.beans.factory.annotation.Value;

public interface CustomerImgHomestayResponse extends IsIdentified {

    @Value("#{target.imgUrl}")
    String getImgUrl();

}
