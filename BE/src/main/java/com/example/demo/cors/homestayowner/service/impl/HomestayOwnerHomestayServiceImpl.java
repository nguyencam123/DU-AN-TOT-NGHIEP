package com.example.demo.cors.homestayowner.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerHomestayReponse;
import com.example.demo.cors.homestayowner.model.request.HomestayownerHomestayRequest;
import com.example.demo.cors.homestayowner.repository.*;
import com.example.demo.cors.homestayowner.service.HomestayOwnerHomestayService;
import com.example.demo.entities.Homestay;
import com.example.demo.entities.ImgHomestay;
import com.example.demo.infrastructure.contant.Status;
import com.example.demo.infrastructure.exception.rest.RestApiException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HomestayOwnerHomestayServiceImpl implements HomestayOwnerHomestayService {

    @Autowired
    private HomestayOwnerHomestayRepository homestayownerHomestayRepository;

    @Autowired
    private HomestayOwnerProvinceRepository provinceRepository;

    @Autowired
    private HomestayOwnerRegionRepository regionRepository;

    @Autowired
    private HomestayOwnerImgHomestayRepo homestayOwnerImgHomestayRepo;

    @Autowired
    private HomestayOwnerOwnerHomestayRepository homestayOwnerOwnerHomestayRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public PageableObject<Homestay> getPageHomestay(String id,HomestayownerHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(),request.getSize());
        Page<Homestay> res=homestayownerHomestayRepository.getHomestayByOwnerH(id,pageable);
        return new PageableObject<>(res);
    }

    @Override
    public PageableObject<Homestay> getAll(HomestayownerHomestayRequest homestayownerHomestayRequest) {
        Pageable pageable = PageRequest.of(homestayownerHomestayRequest.getPage(),homestayownerHomestayRequest.getSize());
        Page<Homestay> res=homestayownerHomestayRepository.findAll(pageable);
        return new PageableObject<>(res);
    }

    @Override
    public PageableObject<HomestayOwnerHomestayReponse> getAllPageable(HomestayownerHomestayRequest homestayownerHomestayRequest) {
        Pageable pageable = PageRequest.of(homestayownerHomestayRequest.getPage(),homestayownerHomestayRequest.getSize());
        Page<HomestayOwnerHomestayReponse> res=homestayownerHomestayRepository.getALlHomestayPage(pageable);
        return new PageableObject<>(res);
    }

    @Override
    public Homestay addHomestays(HomestayownerHomestayRequest request, List<MultipartFile> multipartFiles) throws IOException{
        Homestay homestay=new Homestay();
        homestay.setName(request.getName());
        homestay.setStartDate(request.getStartDate());
        homestay.setEndDate(request.getEndDate());
        homestay.setDesc(request.getDesc());
        homestay.setPrice(request.getPrice());
        homestay.setNumberPerson(request.getNumberPerson());
        homestay.setAddress(request.getAddress());
        homestay.setProvince(provinceRepository.findById(request.getProvince()).orElse(null));
        homestay.setRegion(regionRepository.findById(request.getRegion()).orElse(null));
        homestay.setOwnerHomestay(homestayOwnerOwnerHomestayRepository.findById(request.getOwnerHomestay()).orElse(null));
        homestay.setStatus(Status.KHONG_HOAT_DONG);
        Homestay homestay1=homestayownerHomestayRepository.save(homestay);
        List<ImgHomestay> newImages = new ArrayList<>();
        for (MultipartFile image : multipartFiles) {
            ImgHomestay imgHomestay = new ImgHomestay();
            imgHomestay.setHomestay(homestay1);
            Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.asMap("folder", "homestay_images"));
            imgHomestay.setImgUrl(uploadResult.get("url").toString());
            homestayOwnerImgHomestayRepo.save(imgHomestay);
            newImages.add(imgHomestay);
        }
        homestay1.setImages(newImages);
        return homestay1;
    }

    @Override
    @Transactional
    public Homestay updateHomestays(String id, HomestayownerHomestayRequest request, List<MultipartFile> multipartFiles) throws IOException {
        Homestay homestay = homestayownerHomestayRepository.findById(id).orElse(null);
        homestay.setName(request.getName());
        homestay.setStartDate(request.getStartDate());
        homestay.setEndDate(request.getEndDate());
        homestay.setDesc(request.getDesc());
        homestay.setPrice(request.getPrice());
        homestay.setNumberPerson(request.getNumberPerson());
        homestay.setAddress(request.getAddress());
        homestay.setProvince(provinceRepository.findById(request.getProvince()).orElse(null));
        homestay.setRegion(regionRepository.findById(request.getRegion()).orElse(null));
        homestay.setOwnerHomestay(homestayOwnerOwnerHomestayRepository.findById(request.getOwnerHomestay()).orElse(null));
        Homestay homestay1=homestayownerHomestayRepository.save(homestay);
        homestayOwnerImgHomestayRepo.deleteByHomestay(id);
        List<ImgHomestay> newImages = new ArrayList<>();
        for (MultipartFile image : multipartFiles) {
            ImgHomestay imgHomestay = new ImgHomestay();
            imgHomestay.setHomestay(homestay1);
            Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.asMap("folder", "homestay_images"));
            imgHomestay.setImgUrl(uploadResult.get("url").toString());
            homestayOwnerImgHomestayRepo.save(imgHomestay);
            newImages.add(imgHomestay);
        }
        return homestay1;
    }

}
