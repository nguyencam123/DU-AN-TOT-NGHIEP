package com.example.demo.cors.homestayowner.service;

import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerImgHomestayReponse;

import java.util.List;

public interface HomestayOwnerImgHomestayService {

    List<HomestayOwnerImgHomestayReponse> getImgHomestayByHomestayId(String id);

}
