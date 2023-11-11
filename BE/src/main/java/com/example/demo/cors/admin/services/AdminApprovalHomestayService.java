package com.example.demo.cors.admin.services;

import com.example.demo.cors.admin.model.request.AdminApprovalRequest;
import com.example.demo.entities.Homestay;

public interface AdminApprovalHomestayService {

    Homestay adminApprovalHomestay(AdminApprovalRequest request);

    Homestay adminRefuseHomestay(AdminApprovalRequest request);

}
