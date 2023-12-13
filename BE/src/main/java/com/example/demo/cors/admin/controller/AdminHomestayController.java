package com.example.demo.cors.admin.controller;

import com.example.demo.cors.admin.model.request.AdminApprovalRequest;
import com.example.demo.cors.admin.model.request.AdminHomestayRequest;
import com.example.demo.cors.admin.services.AdminApprovalHomestayService;
import com.example.demo.cors.admin.services.AdminHomestayService;
import com.example.demo.cors.common.base.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v3/homestay")
@PreAuthorize("hasRole('ADMIN')")
public class AdminHomestayController {

    @Autowired
    private AdminHomestayService adminHomestayService;

    @Autowired
    private AdminApprovalHomestayService adminApprovalHomestayService;

    @GetMapping("/get-admin-by-token")
    public ResponseObject getOwnerByToken(@RequestParam("token") String token) {
        return new ResponseObject(adminHomestayService.getAdminByToken(token));
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseObject getAll(final AdminHomestayRequest adminHomestayRequest) {
        return new ResponseObject(adminHomestayService.getAllHomestay(adminHomestayRequest));
    }

    @PutMapping("/approve")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseObject adminApprovalHomestay(@RequestBody AdminApprovalRequest request) {
        return new ResponseObject(adminApprovalHomestayService.adminApprovalHomestay(request));
    }

    @PutMapping("/refuse")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseObject adminRefuseHomestay(@RequestBody AdminApprovalRequest request) {
        return new ResponseObject(adminApprovalHomestayService.adminRefuseHomestay(request));
    }

}
