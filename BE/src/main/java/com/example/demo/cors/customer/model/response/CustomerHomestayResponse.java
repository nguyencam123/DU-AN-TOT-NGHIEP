package com.example.demo.cors.customer.model.response;

import com.example.demo.entities.ConvenientHomestay;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface CustomerHomestayResponse {

    @Value("#{target.imageUrls}")
    List<ConvenientHomestay> getImageUrls();

}
