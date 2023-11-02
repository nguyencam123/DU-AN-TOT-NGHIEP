package com.example.demo.cors.homestayowner.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerOwnerHomestayRequest;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerUsenamePasswordRequest;
import com.example.demo.cors.homestayowner.model.request.HomestayownerLoginRequest;
import com.example.demo.cors.homestayowner.service.HomestayOwnerLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v2/login")
@RequiredArgsConstructor
public class HomestayOwnerLoginController {

    @Autowired
    private HomestayOwnerLoginService homestayownerLoginService;

    @PostMapping("")
    public ResponseObject loginHomestayOwnerHomestay(@RequestBody HomestayownerLoginRequest homestayownerLoginRequest) {
        return new ResponseObject(homestayownerLoginService.login(homestayownerLoginRequest));
    }

    @PostMapping("/authenticate")
    public ResponseObject authenticate(@RequestBody HomestayOwnerUsenamePasswordRequest request){
        return new ResponseObject(homestayownerLoginService.authenticate(request));
    }

    @PostMapping("/registers")
    public ResponseObject registers(@RequestBody HomestayOwnerOwnerHomestayRequest request){
        return new ResponseObject(homestayownerLoginService.register(request));
    }

}
