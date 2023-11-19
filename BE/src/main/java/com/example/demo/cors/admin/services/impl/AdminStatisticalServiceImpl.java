package com.example.demo.cors.admin.services.impl;

import com.example.demo.cors.admin.model.request.AdminStatisticalRequest;
import com.example.demo.cors.admin.model.response.AdminStatisticalReponse;
import com.example.demo.cors.admin.repository.AdminBookingRepository;
import com.example.demo.cors.admin.services.AdminStatisticalService;
import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerStatisticalReponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminStatisticalServiceImpl implements AdminStatisticalService {

    @Autowired
    private AdminBookingRepository adminBookingRepository;

    @Override
    public AdminStatisticalReponse getThongKe(AdminStatisticalRequest adminStatisticalRequest) {
        return adminBookingRepository.getThongKe(adminStatisticalRequest);
    }

    @Override
    public List<AdminStatisticalReponse> getAllStatisticalForAllMonthsInYear(AdminStatisticalRequest request) {
        List<AdminStatisticalReponse> responseList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            request.setYear(request.getYear());
            request.setMonth(i);
            responseList.add(adminBookingRepository.getThongKe(request));
        }
        return responseList;
    }

}
