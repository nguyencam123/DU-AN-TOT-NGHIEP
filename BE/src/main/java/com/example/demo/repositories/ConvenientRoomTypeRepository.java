package com.example.demo.repositories;

import com.example.demo.entities.ConvenientRoomType;
import com.example.demo.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(ConvenientRoomTypeRepository.NAME)
public interface ConvenientRoomTypeRepository extends JpaRepository<ConvenientRoomType, Room> {
    public static final String NAME = "BaseConvenientRoomTypeRepository";
}
