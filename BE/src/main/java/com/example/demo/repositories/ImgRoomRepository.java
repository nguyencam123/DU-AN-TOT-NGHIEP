package com.example.demo.repositories;

import com.example.demo.entities.ImgRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(ImgRoomRepository.NAME)
public interface ImgRoomRepository extends JpaRepository<ImgRoom ,String> {
    public static final String NAME = "BaseImgRoomRepository";

}
