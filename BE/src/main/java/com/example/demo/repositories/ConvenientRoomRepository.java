package com.example.demo.repositories;

import com.example.demo.entities.ConvenientRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(ConvenientRoomRepository.NAME)
public interface ConvenientRoomRepository extends JpaRepository<ConvenientRoom, String> {
    public static final String NAME = "BaseConvenientRoomRepository";
}
