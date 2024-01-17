package com.example.demo.cors.homestayowner.repository;

import com.example.demo.entities.Booking;
import com.example.demo.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HomestayOwnerUserRepository extends UserRepository {

    boolean existsByUsername(String username);

}
