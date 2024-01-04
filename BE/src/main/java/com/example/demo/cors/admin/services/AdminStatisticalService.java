package com.example.demo.cors.admin.services;

import com.example.demo.cors.admin.model.request.AdminStatisticalRequest;
import com.example.demo.cors.admin.model.request.AdminStatisticalTop5Request;
import com.example.demo.cors.admin.model.response.AdminStatisticalReponse;
import com.example.demo.cors.admin.model.response.AdminStatisticalTop5Response;
import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerStatisticalReponse;
import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerStatisticalTop5Reponse;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerStatisticalRequest;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerTop5StatisticalRequest;

import java.util.List;

public interface AdminStatisticalService {

    AdminStatisticalReponse getStatistical(String id);

    AdminStatisticalReponse getStatisticalbyMonthAndYear(AdminStatisticalRequest request);

    List<AdminStatisticalReponse> getAllStatisticalForAllMonthsInYear(AdminStatisticalRequest request);

    List<AdminStatisticalTop5Response> getTop5HomestayInYear(AdminStatisticalTop5Request request);

}
