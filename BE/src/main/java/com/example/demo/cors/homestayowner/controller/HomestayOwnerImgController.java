package com.example.demo.cors.homestayowner.controller;


import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.homestayowner.service.HomestayOwnerImgHomestayService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v2/img")
@RequiredArgsConstructor
public class HomestayOwnerImgController {

    @Autowired
    private HomestayOwnerImgHomestayService homestayOwnerImgHomestayService;

    @GetMapping("")
    public ResponseObject getAllHomestayownerHomestay(@RequestParam("id") String id) {
        return new ResponseObject(homestayOwnerImgHomestayService.searchImgHomestay(id));
    }



}
