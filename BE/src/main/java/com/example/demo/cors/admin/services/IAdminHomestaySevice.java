package com.example.demo.cors.admin.services;

import com.example.demo.cors.admin.model.reponse.AdminHomestayReponsi;
import com.example.demo.cors.admin.model.request.AdminHomestayRequest;
import com.example.demo.cors.common.base.PageableObject;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAdminHomestaySevice {
    PageableObject<AdminHomestayReponsi> getAll(AdminHomestayRequest request);


}
