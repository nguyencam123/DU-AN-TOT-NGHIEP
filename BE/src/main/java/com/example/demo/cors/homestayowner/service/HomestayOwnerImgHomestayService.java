package com.example.demo.cors.homestayowner.service;

import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerImgHomestayReponse;
import com.example.demo.entities.Homestay;
import com.example.demo.entities.ImgHomestay;

import java.util.List;

public interface HomestayOwnerImgHomestayService {

    List<HomestayOwnerImgHomestayReponse> getImgHomestayByHomestayId(String id);

    List<ImgHomestay> searchImgHomestay(String homestayId);

    Homestay AddImgHomestay(String idHomestay);

}