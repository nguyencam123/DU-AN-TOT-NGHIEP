package com.example.demo.cors.homestayowner.service;

import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerStatisticalReponse;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerStatisticalRequest;

import java.util.List;

public interface HomestayOwnerStatisticalServie {

    HomestayOwnerStatisticalReponse getStatistical(String id);

    HomestayOwnerStatisticalReponse getStatisticalbyMonthAndYear(HomestayOwnerStatisticalRequest request);

    List<HomestayOwnerStatisticalReponse> getAllStatisticalForAllMonthsInYear(HomestayOwnerStatisticalRequest request);

}
