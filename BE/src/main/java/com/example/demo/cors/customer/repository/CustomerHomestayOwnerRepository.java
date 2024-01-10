package com.example.demo.cors.customer.repository;

import com.example.demo.repositories.OwnerHomestayRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerHomestayOwnerRepository extends OwnerHomestayRepository {

    boolean existsByUsername(String username);
}
