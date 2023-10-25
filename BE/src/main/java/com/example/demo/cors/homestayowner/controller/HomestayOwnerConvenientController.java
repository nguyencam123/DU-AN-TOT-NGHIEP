package com.example.demo.cors.homestayowner.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.homestayowner.model.request.HomestayownerHomestayRequest;
import com.example.demo.cors.homestayowner.service.HomestayOwnerConvenientSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v2/convenient/")
public class HomestayOwnerConvenientController {

    @Autowired
    private HomestayOwnerConvenientSerivce homestayOwnerConvenientSerivce;

    @GetMapping("")
    public ResponseObject getConvenient(){
        return new ResponseObject(homestayOwnerConvenientSerivce.getHomestayOwnerConvenientHomestay());
    }

    @GetMapping("get-homestay-by-convenient")
    public ResponseObject getHomestayByConvenient(@RequestParam("id") String id,HomestayownerHomestayRequest homestayownerHomestayRequest){
        return new ResponseObject(homestayOwnerConvenientSerivce.getPageablebyConvenient(id,homestayownerHomestayRequest));
    }

}
