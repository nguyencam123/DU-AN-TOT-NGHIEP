package com.example.demo.cors.homestayowner.service.impl;

import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerImgHomestayReponse;
import com.example.demo.cors.homestayowner.repository.HomestayOwnerImgHomestayRepo;
import com.example.demo.cors.homestayowner.service.HomestayOwnerImgHomestayService;
import com.example.demo.entities.Homestay;
import com.example.demo.entities.ImgHomestay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomestayOwnerImgHomestayServiceImpl implements HomestayOwnerImgHomestayService {

    @Autowired
    private HomestayOwnerImgHomestayRepo homestayOwnerImgHomestayRepo;

    @Override
    public List<ImgHomestay> getImgHomestayByHomestayId(String id) {
        return homestayOwnerImgHomestayRepo.getImgHomestayByHomestay(id);
    }

    @Override
    public List<ImgHomestay> searchImgHomestay(String homestayId) {
        return homestayOwnerImgHomestayRepo.getImgHomestayByHomestay(homestayId);
    }

    @Override
    public ImgHomestay AddImgHomestay(String idHomestay) {
        return null;
    }

    @Override
    public ImgHomestay deleteImghomestay(String id) {
        return null;
    }


}
