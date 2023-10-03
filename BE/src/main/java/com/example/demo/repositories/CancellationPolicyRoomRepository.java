package com.example.demo.repositories;

import com.example.demo.models.CancellationPolicyRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancellationPolicyRoomRepository extends JpaRepository<CancellationPolicyRoom, Integer> {
}