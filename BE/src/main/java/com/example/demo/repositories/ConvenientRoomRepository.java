package com.example.demo.repositories;

import com.example.demo.models.ConvenientRoom;
import com.example.demo.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvenientRoomRepository extends JpaRepository<ConvenientRoom, Room> {
}
