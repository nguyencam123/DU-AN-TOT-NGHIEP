package com.example.demo.cors.customer.model.response;

import com.example.demo.entities.base.IsIdentified;
import org.springframework.beans.factory.annotation.Value;

public interface CustomerHotelResponse extends IsIdentified {

    @Value("#{target.stt}")
    String getStt();

    @Value("#{target.name}")
    String getName();

    @Value("#{target.address}")
    String getAddress();

    @Value("#{target.star}")
    String getStar();

}
