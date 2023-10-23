package com.example.demo.cors.homestayowner.service;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerHomestayReponse;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerimgHomeRequest;
import com.example.demo.cors.homestayowner.model.request.HomestayownerHomestayRequest;
import com.example.demo.entities.Homestay;
import com.example.demo.entities.ImgHomestay;

public interface HomestayOwnerHomestayService {

    PageableObject<HomestayOwnerHomestayReponse> getAllPageable(HomestayownerHomestayRequest homestayownerHomestayRequest);

    Homestay addHomestay(HomestayownerHomestayRequest homestayOwnerAddHomestayRequest);

    ImgHomestay addImgHomestay(String id, HomestayOwnerimgHomeRequest request);
}
