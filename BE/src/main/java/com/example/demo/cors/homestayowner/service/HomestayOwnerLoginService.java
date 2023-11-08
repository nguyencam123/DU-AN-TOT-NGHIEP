package com.example.demo.cors.homestayowner.service;

import com.example.demo.cors.homestayowner.model.reponse.loginreponse.HomestayOwnerAuthenticationReponse;
import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerLoginReponse;
import com.example.demo.cors.homestayowner.model.request.loginrequest.HomestayOwnerOwnerHomestayRequest;
import com.example.demo.cors.homestayowner.model.request.loginrequest.HomestayOwnerUsenamePasswordRequest;
import com.example.demo.cors.homestayowner.model.request.loginrequest.HomestayownerLoginRequest;
import com.example.demo.cors.homestayowner.model.request.loginrequest.HomestayOwnerPasswordRequest;
import com.example.demo.entities.Homestay;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

public interface HomestayOwnerLoginService {

    HomestayOwnerAuthenticationReponse register(HomestayOwnerOwnerHomestayRequest homestayOwnerOwnerHomestayRequest);

    HomestayOwnerAuthenticationReponse authenticate(HomestayOwnerUsenamePasswordRequest request);

    HomestayOwnerAuthenticationReponse changePassword(HomestayOwnerPasswordRequest request, Principal connecteUser);

    HomestayOwnerAuthenticationReponse updateInformationOwner(String idOwner,HomestayOwnerOwnerHomestayRequest request, MultipartFile multipartFile) throws IOException;

    String createResetPasswordToken(String username);

}
