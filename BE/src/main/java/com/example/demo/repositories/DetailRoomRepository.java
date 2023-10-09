package com.example.demo.repositories;

import com.example.demo.entities.DetailRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DetailRoomRepository extends JpaRepository<DetailRoom, UUID> {
}
