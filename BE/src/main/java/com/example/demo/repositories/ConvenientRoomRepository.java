package com.example.demo.repositories;

import com.example.demo.entities.ConvenientRoom;
import com.example.demo.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvenientRoomRepository extends JpaRepository<ConvenientRoom, Room> {
}
