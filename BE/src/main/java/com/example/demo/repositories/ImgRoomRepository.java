package com.example.demo.repositories;

import com.example.demo.models.ImgRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgRoomRepository extends JpaRepository<ImgRoom ,Integer> {
}