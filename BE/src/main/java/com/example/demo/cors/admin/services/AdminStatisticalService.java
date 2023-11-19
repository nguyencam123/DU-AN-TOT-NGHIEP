package com.example.demo.cors.admin.services;

import com.example.demo.cors.admin.model.request.AdminStatisticalRequest;
import com.example.demo.cors.admin.model.response.AdminStatisticalReponse;
import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerStatisticalReponse;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerStatisticalRequest;

import java.util.List;

public interface AdminStatisticalService {

    AdminStatisticalReponse getThongKe(AdminStatisticalRequest adminStatisticalRequest);

    List<AdminStatisticalReponse> getAllStatisticalForAllMonthsInYear(AdminStatisticalRequest adminStatisticalRequest);

}
