package com.example.demo.cors.homestayowner.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.homestayowner.model.request.HomestayownerLoginRequest;
import com.example.demo.cors.homestayowner.service.HomestayOwnerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v2/login")
public class HomestayOwnerLoginController {

    @Autowired
    private HomestayOwnerLoginService homestayownerLoginService;

    @PostMapping("")
    public ResponseObject loginHomestayOwnerHomestay(@RequestBody HomestayownerLoginRequest homestayownerLoginRequest) {
        return new ResponseObject(homestayownerLoginService.login(homestayownerLoginRequest));
    }


}
