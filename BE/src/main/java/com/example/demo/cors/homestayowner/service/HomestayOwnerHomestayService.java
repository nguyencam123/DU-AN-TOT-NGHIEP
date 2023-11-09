package com.example.demo.cors.homestayowner.service;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerHomestayDetailReponse;
import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerHomestayReponse;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerDetailHomestayRequest;
import com.example.demo.cors.homestayowner.model.request.HomestayownerHomestayRequest;
import com.example.demo.cors.homestayowner.repository.HomestayOwnerDetailHomestayReposritory;
import com.example.demo.entities.DetailHomestay;
import com.example.demo.entities.Homestay;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface HomestayOwnerHomestayService {

    PageableObject<Homestay> getPageHomestay(String id,HomestayownerHomestayRequest request);

    Homestay updateHomestays(String id,HomestayownerHomestayRequest request,List<MultipartFile> multipartFiles,List<String> idConvenientHomestay) throws IOException;

    Homestay deleteHomestays(String id);

    DetailHomestay addDetailHomestay(HomestayOwnerDetailHomestayRequest request);

    Homestay addHomestay(HomestayownerHomestayRequest request,List<MultipartFile> multipartFiles,List<String> idConvenientHomestay) throws IOException;
}
