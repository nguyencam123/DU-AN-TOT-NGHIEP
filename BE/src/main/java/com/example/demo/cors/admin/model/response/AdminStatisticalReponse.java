package com.example.demo.cors.admin.model.response;

import org.springframework.beans.factory.annotation.Value;

public interface AdminStatisticalReponse {

    @Value("#{target.DoanhSo}")
    String getDoanhSo();

    @Value("#{target.DoanhThu}")
    String getDoanhThu();

    @Value("#{target.HoaHong}")
    String getHoaHong();

}