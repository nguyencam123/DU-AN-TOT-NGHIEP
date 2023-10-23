package com.example.demo.cors.homestayowner.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.homestayowner.model.request.HomestayownerHomestayRequest;
import com.example.demo.cors.homestayowner.service.HomestayOwnerHomestayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v2/homestay/")
public class HomestayOwnerHomestayController {

    @Autowired
    private HomestayOwnerHomestayService homestayownerHomestayService;

    @GetMapping("get-homestay")
    public ResponseObject getPageHomestayownerHomestay(HomestayownerHomestayRequest homestayownerHomestayRequest) {
        return new ResponseObject(homestayownerHomestayService.getAllPageable(homestayownerHomestayRequest));
    }

    @PostMapping("add-homestay")
    public ResponseObject addHomestay(@RequestBody HomestayownerHomestayRequest homestayOwnerAddHomestayRequest){
        return new ResponseObject(homestayownerHomestayService.addHomestay(homestayOwnerAddHomestayRequest));
    }

}
