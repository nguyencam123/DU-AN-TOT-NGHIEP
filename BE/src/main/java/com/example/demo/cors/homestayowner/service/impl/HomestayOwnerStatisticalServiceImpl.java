package com.example.demo.cors.homestayowner.service.impl;

import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerStatisticalReponse;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerStatisticalRequest;
import com.example.demo.cors.homestayowner.repository.HomestayOwnerBookingRepository;
import com.example.demo.cors.homestayowner.service.HomestayOwnerStatisticalServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomestayOwnerStatisticalServiceImpl implements HomestayOwnerStatisticalServie {

    @Autowired
    private HomestayOwnerBookingRepository homestayOwnerBookingRepository;

    @Override
    public HomestayOwnerStatisticalReponse getStatistical(String id) {
        return homestayOwnerBookingRepository.getStatistical(id);
    }

    @Override
    public HomestayOwnerStatisticalReponse getStatisticalbyMonthAndYear(HomestayOwnerStatisticalRequest request) {
        return homestayOwnerBookingRepository.getAllStatistical(request);
    }

    @Override
    public List<HomestayOwnerStatisticalReponse> getAllStatisticalForAllMonthsInYear(HomestayOwnerStatisticalRequest request) {
        List<HomestayOwnerStatisticalReponse> responseList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            request.setYear(request.getYear());
            request.setMonth(i);
            request.setIdOwnerHomestay(request.getIdOwnerHomestay());
            responseList.add(homestayOwnerBookingRepository.getAllStatisticalYear(request));
        }
        return responseList;
    }

}


