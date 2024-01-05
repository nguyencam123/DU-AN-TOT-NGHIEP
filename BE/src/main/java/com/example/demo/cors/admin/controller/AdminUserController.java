package com.example.demo.cors.admin.controller;

import com.example.demo.cors.admin.model.request.AdminConvenientHomestayRequest;
import com.example.demo.cors.admin.model.request.AdminOwnerHomestayAppRequest;
import com.example.demo.cors.admin.model.request.AdminUserRequest;
import com.example.demo.cors.admin.services.AdminUserService;
import com.example.demo.cors.common.base.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v3/user")
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;

    @GetMapping()
    public ResponseObject getAllConvenient(AdminUserRequest request){
        return new ResponseObject(adminUserService.getAllUser(request));
    }

    @PutMapping("/approve")
    public ResponseObject adminApprovalUser(@RequestBody AdminUserRequest request) {
        return new ResponseObject(adminUserService.adminApprovalUser(request));
    }

    @PutMapping("/refuse")
    public ResponseObject adminRefuseUser(@RequestBody AdminUserRequest request) {
        return new ResponseObject(adminUserService.adminRefuseUser(request));
    }
}
