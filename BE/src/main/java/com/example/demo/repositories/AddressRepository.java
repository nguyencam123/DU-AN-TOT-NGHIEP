package com.example.demo.repositories;

import com.example.demo.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(AddressRepository.NAME)
public interface AddressRepository extends JpaRepository<Address, String> {
    public static final String NAME = "BaseAddressRepository";
}
