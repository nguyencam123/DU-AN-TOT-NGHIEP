package com.example.demo.repositories;

import com.example.demo.entities.DetailCancellationPolicyRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(DetailCancellationPolicyRoomRepository.NAME)
public interface DetailCancellationPolicyRoomRepository extends JpaRepository<DetailCancellationPolicyRoom, String> {
    public static final String NAME = "BaseDetailCancellationPolicyRoomRepository";

}
