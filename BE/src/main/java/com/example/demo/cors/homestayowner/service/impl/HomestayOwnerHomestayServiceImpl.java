package com.example.demo.cors.homestayowner.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerHomestayReponse;
import com.example.demo.cors.homestayowner.model.request.HomestayownerHomestayRequest;
import com.example.demo.cors.homestayowner.repository.HomestayOwnerHomestayRepository;
import com.example.demo.cors.homestayowner.repository.HomestayOwnerImgHomestayRepo;
import com.example.demo.cors.homestayowner.repository.HomestayOwnerProvinceRepository;
import com.example.demo.cors.homestayowner.repository.HomestayOwnerRegionRepository;
import com.example.demo.cors.homestayowner.service.HomestayOwnerHomestayService;
import com.example.demo.entities.Homestay;
import com.example.demo.entities.ImgHomestay;
import com.example.demo.infrastructure.contant.Status;
import jakarta.transaction.Transactional;
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
import java.util.Optional;

@Service
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
    private Cloudinary cloudinary;

    @Override
    public PageableObject<HomestayOwnerHomestayReponse> getAllPageable(HomestayownerHomestayRequest homestayownerHomestayRequest) {
        Pageable pageable = PageRequest.of(homestayownerHomestayRequest.getPage(),homestayownerHomestayRequest.getSize());
        Page<HomestayOwnerHomestayReponse> res=homestayownerHomestayRepository.getALlHomestayPage(pageable);
        return new PageableObject<>(res);
    }

    @Override
    public Homestay addHomestay(HomestayownerHomestayRequest request) {
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
        homestay.setImages(null);
        homestay.setStatus(Status.KHONG_HOAT_DONG);
        return homestayownerHomestayRepository.save(homestay);
    }

    @Override
    public List<ImgHomestay> addImgHomestay(String id, List<MultipartFile> multipartFiles)  throws IOException {
        Homestay homestay = homestayownerHomestayRepository.findById(id).orElse(null);
        List<ImgHomestay> newImages = new ArrayList<>();
        if (homestay != null) {
            for (MultipartFile image : multipartFiles) {
                ImgHomestay imgHomestay = new ImgHomestay();
                imgHomestay.setHomestay(homestay);
                Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.asMap("folder", "homestay_images"));
                imgHomestay.setImgUrl(uploadResult.get("url").toString());
                homestayOwnerImgHomestayRepo.save(imgHomestay);
                newImages.add(imgHomestay);
            }
        }
        return newImages;
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
