package com.example.demo.cors.admin.controller;

import com.example.demo.cors.admin.model.request.AdminApprovalRequest;
import com.example.demo.cors.admin.model.request.AdminChangeStatusHomestayRequest;
import com.example.demo.cors.admin.model.request.AdminHomestayRequest;
import com.example.demo.cors.admin.services.AdminApprovalService;
import com.example.demo.cors.admin.services.AdminHomestayService;
import com.example.demo.cors.common.base.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v3/homestay")
public class AdminHomestayController {

    @Autowired
    private AdminHomestayService adminHomestayService;

    @Autowired
    private AdminApprovalService adminApprovalService;

    @GetMapping("")
    public ResponseObject getAllHomestay(AdminHomestayRequest adminHomestayRequest){
        return new ResponseObject(adminHomestayService.getAllHomestay(adminHomestayRequest));
    }

    @GetMapping("/cho-duyet")
    public ResponseObject getAllChoDuyet(AdminHomestayRequest adminHomestayRequest){
        return new ResponseObject(adminHomestayService.getAllChoDuyet(adminHomestayRequest));
    }

    @GetMapping("/da-duyet")
    public ResponseObject getAllDaDuyet(AdminHomestayRequest adminHomestayRequest){
        return new ResponseObject(adminHomestayService.getAllDaDuyet(adminHomestayRequest));
    }

    @GetMapping("/da-xoa")
    public ResponseObject getAllDaXoa(AdminHomestayRequest adminHomestayRequest){
        return new ResponseObject(adminHomestayService.getAllDaXoa(adminHomestayRequest));
    }

    @GetMapping("/khong-duyet")
    public ResponseObject getAllKhongDuyet(AdminHomestayRequest adminHomestayRequest){
        return new ResponseObject(adminHomestayService.getAllKhongDuyet(adminHomestayRequest));
    }

    @PostMapping("/agree")
    public ResponseObject agree(@RequestBody AdminApprovalRequest adminApprovalRequest) {
        return new ResponseObject(adminApprovalService.agree(adminApprovalRequest));
    }

    @PostMapping("/disAgree")
    public ResponseObject disAgree(@RequestBody AdminApprovalRequest adminApprovalRequest) {
        return new ResponseObject(adminApprovalService.disAgree(adminApprovalRequest));
    }

    @GetMapping("/byid")
    public ResponseObject getAllById(@RequestBody AdminHomestayRequest adminHomestayRequest){
        return new ResponseObject(adminHomestayService.getAllById(adminHomestayRequest));
    }

    @GetMapping("/bynamecho")
    public ResponseObject findByNameChoDuyet(@RequestBody AdminHomestayRequest adminHomestayRequest){
        return new ResponseObject(adminHomestayService.findByNameChoDuyet(adminHomestayRequest));
    }

    @GetMapping("/bynameda")
    public ResponseObject findByNameDaDuyet(@RequestBody AdminHomestayRequest adminHomestayRequest){
        return new ResponseObject(adminHomestayService.findByNameDaDuyet(adminHomestayRequest));
    }
}
