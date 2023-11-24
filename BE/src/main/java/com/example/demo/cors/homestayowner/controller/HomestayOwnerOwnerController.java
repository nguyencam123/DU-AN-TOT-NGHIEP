package com.example.demo.cors.homestayowner.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.homestayowner.model.conventer.HomestayOwnerOwnerConventer;
import com.example.demo.cors.homestayowner.model.request.loginrequest.HomestayOwnerOwnerHomestayRequest;
import com.example.demo.cors.homestayowner.service.HomestayOwnerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v2/owner")
@PreAuthorize("hasRole('OWNER')")
public class HomestayOwnerOwnerController {

    @Autowired
    private HomestayOwnerLoginService homestayownerLoginService;

    @Autowired
    private HomestayOwnerOwnerConventer conventer;


    @PutMapping("/update-information-owner")
    @PreAuthorize("hasAuthority('owner:update')")
    public ResponseObject updateInformationOwners(@RequestParam("owner") String homestayOwnerOwnerHomestayRequest, @RequestParam("id") String idOwner){
        HomestayOwnerOwnerHomestayRequest request=conventer.convert(homestayOwnerOwnerHomestayRequest);
        return new ResponseObject(homestayownerLoginService.updateInformationOwner(idOwner,request));
    }

    @PutMapping("/update-information-imgowner")
    @PreAuthorize("hasAuthority('owner:update')")
    public ResponseObject updateInformationImgOwners(@RequestParam("id") String idOwner, @RequestParam("avataUrl")MultipartFile multipartFile) throws IOException {
        return new ResponseObject(homestayownerLoginService.updateInformationImgOwner(idOwner,multipartFile));
    }

}
