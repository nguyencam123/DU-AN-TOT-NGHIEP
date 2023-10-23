package com.example.demo.cors.homestayowner.service;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerHomestayReponse;
import com.example.demo.cors.homestayowner.model.request.HomestayownerHomestayRequest;
import com.example.demo.entities.Homestay;
import com.example.demo.entities.ImgHomestay;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HomestayOwnerHomestayService {

    PageableObject<HomestayOwnerHomestayReponse> getAllPageable(HomestayownerHomestayRequest homestayownerHomestayRequest);

    Homestay addHomestay(HomestayownerHomestayRequest homestayOwnerAddHomestayRequest);

}
