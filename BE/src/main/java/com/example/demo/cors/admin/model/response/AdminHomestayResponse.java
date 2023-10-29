package com.example.demo.cors.admin.model.response;

import org.springframework.beans.factory.annotation.Value;

public interface AdminHomestayResponse {

    @Value("#{target.name}")
    String getName();

    @Value("#{target.address}")
    String getAddressName();

    @Value("#{target.name_homestay}")
    String getOwnerHomestayName();

    @Value("#{target.phone_number}")
    String getOwnerHomestayPhoneNumber();

    @Value("#{target.email}")
    String getOwnerHomestayEmail();

    @Value("#{target.status}")
    String getStatus();
}
