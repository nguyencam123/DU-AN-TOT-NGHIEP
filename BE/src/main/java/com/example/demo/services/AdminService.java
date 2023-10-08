package com.example.demo.services;

import com.example.demo.entities.Admin;

import java.util.List;

public interface AdminService {

    List<Admin> getAll();

    Admin getAdmin();

}
