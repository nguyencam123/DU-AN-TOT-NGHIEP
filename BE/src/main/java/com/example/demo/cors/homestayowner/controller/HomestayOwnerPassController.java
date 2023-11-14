package com.example.demo.cors.homestayowner.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.homestayowner.model.request.loginrequest.HomestayOwnerPasswordRequest;
import com.example.demo.cors.homestayowner.model.request.loginrequest.ResetPasswordData;
import com.example.demo.cors.homestayowner.service.HomestayOwnerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v2/change-pass")
public class HomestayOwnerPassController {

    @Autowired
    private HomestayOwnerLoginService homestayownerLoginService;

    @PostMapping("/changePassword")
    public ResponseObject changePassword(@RequestBody HomestayOwnerPasswordRequest request, Principal connecteUser){
        return new ResponseObject(homestayownerLoginService.changePassword(request,connecteUser));
    }

}
