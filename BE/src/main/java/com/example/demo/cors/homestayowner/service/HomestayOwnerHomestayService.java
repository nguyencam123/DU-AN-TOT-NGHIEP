package com.example.demo.cors.homestayowner.service;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerHomestayReponse;
import com.example.demo.cors.homestayowner.model.request.HomestayownerHomestayRequest;

public interface HomestayOwnerHomestayService {

    PageableObject<HomestayOwnerHomestayReponse> getAllPageable(HomestayownerHomestayRequest homestayownerHomestayRequest);

}
