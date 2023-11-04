package com.example.demo.cors.homestayowner.service;

import com.example.demo.cors.homestayowner.model.reponse.loginreponse.HomestayOwnerAuthenticationReponse;
import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerLoginReponse;
import com.example.demo.cors.homestayowner.model.request.loginrequest.HomestayOwnerOwnerHomestayRequest;
import com.example.demo.cors.homestayowner.model.request.loginrequest.HomestayOwnerUsenamePasswordRequest;
import com.example.demo.cors.homestayowner.model.request.loginrequest.HomestayownerLoginRequest;
import com.example.demo.cors.homestayowner.model.request.loginrequest.HomestayOwnerPasswordRequest;

import java.security.Principal;

public interface HomestayOwnerLoginService {

    HomestayOwnerLoginReponse login(HomestayownerLoginRequest homestayownerLoginRequest);

    HomestayOwnerAuthenticationReponse register(HomestayOwnerOwnerHomestayRequest homestayOwnerOwnerHomestayRequest);

    HomestayOwnerAuthenticationReponse authenticate(HomestayOwnerUsenamePasswordRequest request);

    HomestayOwnerAuthenticationReponse changePassword(HomestayOwnerPasswordRequest request, Principal connecteUser);

}
