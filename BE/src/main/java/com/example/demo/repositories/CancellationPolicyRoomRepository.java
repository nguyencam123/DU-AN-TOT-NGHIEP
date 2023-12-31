package com.example.demo.repositories;

import com.example.demo.entities.CancellationPolicyRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(CancellationPolicyRoomRepository.NAME)
public interface CancellationPolicyRoomRepository extends JpaRepository<CancellationPolicyRoom, String> {
    public static final String NAME = "BaseCancellationPolicyRoomRepository";
}
