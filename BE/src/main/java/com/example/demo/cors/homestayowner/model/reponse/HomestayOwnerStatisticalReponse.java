package com.example.demo.cors.homestayowner.model.reponse;

import org.springframework.beans.factory.annotation.Value;

public interface HomestayOwnerStatisticalReponse {

    @Value("#{target.DoanhSo}")
    String getDoanhSo();

    @Value("#{target.TongSoTien}")
    String getTongSoTien();
}
