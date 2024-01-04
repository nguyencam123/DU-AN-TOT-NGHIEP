package com.example.demo.cors.admin.model.response;

import org.springframework.beans.factory.annotation.Value;

public interface AdminStatisticalTop5Response {
    @Value("#{target.DoanhSo}")
    String getDoanhSo();

    @Value("#{target.TongSoTien}")
    String getTongSoTien();

    @Value("#{target.name}")
    String getName();

    @Value("#{target.address}")
    String getAddress();

    @Value("#{target.roomNumber}")
    String getRoomNumber();
}
