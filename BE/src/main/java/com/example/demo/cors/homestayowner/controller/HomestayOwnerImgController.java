package com.example.demo.cors.homestayowner.controller;


import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.homestayowner.service.HomestayOwnerImgHomestayService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v2/img")
@RequiredArgsConstructor
@PreAuthorize("hasRole('OWNER')")
public class HomestayOwnerImgController {

    @Autowired
    private HomestayOwnerImgHomestayService homestayOwnerImgHomestayService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('owner:read')")
    public ResponseObject getAllHomestayownerHomestay(@RequestParam("id") String id) {
        return new ResponseObject(homestayOwnerImgHomestayService.searchImgHomestay(id));
    }



}
