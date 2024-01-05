package com.example.demo.cors.admin.services;

import com.example.demo.cors.admin.model.request.AdminConvenientHomestayRequest;
import com.example.demo.cors.admin.model.request.AdminUserRequest;
import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.entities.ConvenientHomestay;
import com.example.demo.entities.User;

public interface AdminUserService {
    User adminApprovalUser(AdminUserRequest request);

    User adminRefuseUser(AdminUserRequest request);

    PageableObject<User> getAllUser(AdminUserRequest request);
}
