package com.example.demo.cors.admin.model.response;

import com.example.demo.entities.base.IsIdentified;
import org.springframework.beans.factory.annotation.Value;

public interface AdminBookingResponse extends IsIdentified {

    @Value("#{target.stt}")
    String getStt();

    @Value("#{target.user_name}")
    String getUserName();

    @Value("#{target.status}")
    String getStatus();

    @Value("#{target.created_date}")
    Long getCreatedDate();

    @Value("#{target.start_date}")
    Long getStartDate();

    @Value("#{target.end_date}")
    Long getEndDate();

    @Value("#{target.homestay_name}")
    String getHomestayName();

}
