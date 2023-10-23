package com.example.demo.cors.homestayowner.service.impl;

import com.cloudinary.Cloudinary;
import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerHomestayReponse;
import com.example.demo.cors.homestayowner.model.request.HomestayownerHomestayRequest;
import com.example.demo.cors.homestayowner.repository.*;
import com.example.demo.cors.homestayowner.service.HomestayOwnerHomestayService;
import com.example.demo.entities.*;
import com.example.demo.infrastructure.contant.StatusServicePack;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class HomestayOwnerHomestayServiceImpl implements HomestayOwnerHomestayService {

    @Autowired
    private HomestayOwnerHomestayRepository homestayownerHomestayRepository;
    @Autowired
    private HomestayOwnerProvinceRepository provinceRepository;
    @Autowired
    private HomestayOwnerRegionRepository regionRepository;
    @Autowired
    private HomestayOwnerAddressRepository addressRepository;
    @Autowired
    private HomestayOwnerImgHomestayRepo imgHomestayRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public PageableObject<HomestayOwnerHomestayReponse> getAllPageable(HomestayownerHomestayRequest homestayownerHomestayRequest) {
        Pageable pageable = PageRequest.of(homestayownerHomestayRequest.getPage(),homestayownerHomestayRequest.getSize());
        Page<HomestayOwnerHomestayReponse> res=homestayownerHomestayRepository.getALlHomestayPage(pageable);
        return new PageableObject<>(res);
    }

    @Override
    @Transactional
    public Homestay addHomestay(HomestayownerHomestayRequest homestayOwnerAddHomestayRequest){
        Homestay homestay = new Homestay();
//        List<ImgHomestay> images =new ArrayList<>();
//        for (MultipartFile file : multipartFileImages) {
//            ImgHomestay imgHomestay = new ImgHomestay();
//            imgHomestay.setHomestay(homestay);
//            imgHomestay.setImgUrl(cloudinary.uploader()
//                    .upload(file.getBytes(), Map.of("id", UUID.randomUUID().toString()))
//                    .get("url")
//                    .toString());
//            images.add(imgHomestay);
//        }
        homestay.setName(homestayOwnerAddHomestayRequest.getName());
        homestay.setStartDate(homestayOwnerAddHomestayRequest.getStartDate());
        Address address = addressRepository.findById(homestayOwnerAddHomestayRequest.getAddress()).orElse(null);
        Region region = regionRepository.findById(homestayOwnerAddHomestayRequest.getRegion()).orElse(null);
        Province province = provinceRepository.findById(homestayOwnerAddHomestayRequest.getProvince()).orElse(null);
        homestay.setAddress(address);
        homestay.setRegion(region);
        homestay.setProvince(province);
        homestay.setImages(null);
        homestay.setStatusServicePack(StatusServicePack.CON_HAN);
        return homestayownerHomestayRepository.save(homestay);
    }



}
