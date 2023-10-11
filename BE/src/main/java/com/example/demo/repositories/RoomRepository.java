package com.example.demo.repositories;

import com.example.demo.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(RoomRepository.NAME)
public interface RoomRepository extends JpaRepository<Room, String> {
    public static final String NAME = "BaseRoomRepository";
}
