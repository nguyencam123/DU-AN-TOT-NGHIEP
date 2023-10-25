package com.example.demo.cors.homestayowner.service;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerConvenientReponse;
import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerHomestayReponse;
import com.example.demo.cors.homestayowner.model.request.HomestayownerHomestayRequest;

import java.util.List;

public interface HomestayOwnerConvenientSerivce {

    List<HomestayOwnerConvenientReponse> getHomestayOwnerConvenientHomestay();

    PageableObject<HomestayOwnerHomestayReponse> getPageablebyConvenient(String id,HomestayownerHomestayRequest homestayownerHomestayRequest);

}
