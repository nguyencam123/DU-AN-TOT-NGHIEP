package com.example.demo.cors.homestayowner.service;

import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerImgHomestayReponse;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerimgHomeRequest;
import com.example.demo.entities.ImgHomestay;

import java.io.IOException;

public interface HomestayOwnerImgHomestayService {

    HomestayOwnerImgHomestayReponse getImgHomestayByHomestayId(String id);

    ImgHomestay saveHomestayWithImages(String id,HomestayOwnerimgHomeRequest request) throws IOException;

}
