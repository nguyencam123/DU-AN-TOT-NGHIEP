package com.example.demo.cors.homestayowner.service;

import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerAuthenticationReponse;
import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerLoginReponse;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerOwnerHomestayRequest;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerUsenamePasswordRequest;
import com.example.demo.cors.homestayowner.model.request.HomestayownerLoginRequest;

public interface HomestayOwnerLoginService {

    HomestayOwnerLoginReponse login(HomestayownerLoginRequest homestayownerLoginRequest);

    HomestayOwnerAuthenticationReponse register(HomestayOwnerOwnerHomestayRequest homestayOwnerOwnerHomestayRequest);

    HomestayOwnerAuthenticationReponse authenticate(HomestayOwnerUsenamePasswordRequest request);

}
