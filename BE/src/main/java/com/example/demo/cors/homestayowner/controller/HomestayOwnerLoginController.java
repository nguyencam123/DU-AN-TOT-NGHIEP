package com.example.demo.cors.homestayowner.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.homestayowner.model.request.loginrequest.HomestayOwnerOwnerHomestayRequest;
import com.example.demo.cors.homestayowner.model.request.loginrequest.HomestayOwnerUsenamePasswordRequest;
import com.example.demo.cors.homestayowner.model.request.loginrequest.HomestayownerLoginRequest;
import com.example.demo.cors.homestayowner.service.HomestayOwnerLoginService;
import com.example.demo.entities.Homestay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v2/login")
public class HomestayOwnerLoginController {

    @Autowired
    private HomestayOwnerLoginService homestayownerLoginService;


    @PostMapping("/authenticate")
    public ResponseObject authenticate(@RequestBody HomestayOwnerUsenamePasswordRequest request){
        return new ResponseObject(homestayownerLoginService.authenticate(request));
    }

    @PostMapping("/registers")
    public ResponseObject registers(@RequestBody HomestayOwnerOwnerHomestayRequest request){
        return new ResponseObject(homestayownerLoginService.register(request));
    }

}
