package com.example.demo.repositories;

import com.example.demo.entities.DetailRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository(DetailRoomRepository.NAME)
public interface DetailRoomRepository extends JpaRepository<DetailRoom, String> {
    public static final String NAME = "BaseDetailRoomRepository";
}
