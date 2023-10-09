package com.example.demo.services;

import com.example.demo.entities.Admin;
import com.example.demo.request.AdminRequest;

import java.util.List;

public interface AdminService {

    List<Admin> getAll();

    Admin getAdmin(AdminRequest adminRequest);

}
