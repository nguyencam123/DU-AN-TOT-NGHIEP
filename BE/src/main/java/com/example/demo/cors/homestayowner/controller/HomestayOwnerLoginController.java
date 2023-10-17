package com.example.demo.cors.homestayowner.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.homestayowner.model.request.HomestayownerLoginRequest;
import com.example.demo.cors.homestayowner.service.HomestayOwnerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v2/login")
public class HomestayOwnerLoginController {

    @Autowired
    private HomestayOwnerLoginService homestayownerLoginService;

    @PostMapping("")
    public ResponseObject loginHomestayOwnerHomestay(HomestayownerLoginRequest homestayownerLoginRequest) {
        return new ResponseObject(homestayownerLoginService.login(homestayownerLoginRequest));
    }

}
