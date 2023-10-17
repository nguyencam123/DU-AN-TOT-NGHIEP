package com.example.demo.cors.homestayowner.service;

import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerLoginReponse;
import com.example.demo.cors.homestayowner.model.request.HomestayownerLoginRequest;

public interface HomestayOwnerLoginService {

    HomestayOwnerLoginReponse login(HomestayownerLoginRequest homestayownerLoginRequest);

}
