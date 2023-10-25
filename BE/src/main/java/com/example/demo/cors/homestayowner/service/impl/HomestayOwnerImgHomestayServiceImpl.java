package com.example.demo.cors.homestayowner.service.impl;

import com.cloudinary.Cloudinary;
import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerImgHomestayReponse;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerimgHomeRequest;
import com.example.demo.cors.homestayowner.repository.HomestayOwnerHomestayRepository;
import com.example.demo.cors.homestayowner.repository.HomestayOwnerImgHomestayRepo;
import com.example.demo.cors.homestayowner.service.HomestayOwnerImgHomestayService;
import com.example.demo.entities.ImgHomestay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class HomestayOwnerImgHomestayServiceImpl implements HomestayOwnerImgHomestayService {

    @Autowired
    private HomestayOwnerImgHomestayRepo homestayOwnerImgHomestayRepo;

    @Autowired
    private HomestayOwnerHomestayRepository homestayownerHomestayRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public HomestayOwnerImgHomestayReponse getImgHomestayByHomestayId(String id) {
        return homestayOwnerImgHomestayRepo.getImgHomestayByHomestayId(id);
    }

    @Override
    public ImgHomestay saveHomestayWithImages(String id,HomestayOwnerimgHomeRequest request) throws IOException {
          List<ImgHomestay> images = new ArrayList<>();
        for (MultipartFile file : request.getImages()) {
            ImgHomestay imgHomestay = new ImgHomestay();
            imgHomestay.setHomestay(homestayownerHomestayRepository.findById(id).orElse(null));
            imgHomestay.setImgUrl(cloudinary.uploader()
                    .upload(file.getBytes(), Map.of("id", UUID.randomUUID().toString()))
                    .get("url")
                    .toString());
            images.add(imgHomestay);
        }
        return null;
    }
}
