package com.example.demo.cors.admin.controller;

import com.example.demo.cors.admin.model.request.AdminApprovalRequest;
import com.example.demo.cors.admin.model.request.AdminOwnerHomestayAppRequest;
import com.example.demo.cors.admin.model.request.AdminOwnerHomestayRequest;
import com.example.demo.cors.admin.services.AdminOwnerHomestayService;
import com.example.demo.cors.common.base.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v3/owner-homestay")
public class AdminOwnerHomestayController {
    @Autowired
    private AdminOwnerHomestayService adminOwnerHomestayService;

    @GetMapping()
    public ResponseObject getAllOwner(final AdminOwnerHomestayRequest adminOwnerHomestayRequest) {
        return new ResponseObject(adminOwnerHomestayService.getAllOwner(adminOwnerHomestayRequest));
    }

    @PutMapping("/approve")
    public ResponseObject adminApprovalOwnerHomestay(@RequestBody AdminOwnerHomestayAppRequest request) {
        return new ResponseObject(adminOwnerHomestayService.adminApprovalOwnerHomestay(request));
    }

    @PutMapping("/refuse")
    public ResponseObject adminRefuseOwnerHomestay(@RequestBody AdminOwnerHomestayAppRequest request) {
        return new ResponseObject(adminOwnerHomestayService.adminRefuseOwnerHomestay(request));
    }

}
