package com.example.demo.cors.admin.repository;

import com.example.demo.cors.admin.model.response.AdminConvenientHomestayResponse;
import com.example.demo.entities.ConvenientHomestay;
import com.example.demo.repositories.ConvenientHomestayRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminConvenientHomestayRepository extends ConvenientHomestayRepository {

    boolean existsByName(String name);
}