package com.example.demo.repositories;

import com.example.demo.entities.ApprovalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(ApprovalHistoryRepository.NAME)
public interface ApprovalHistoryRepository extends JpaRepository<ApprovalHistory, String> {
    public static final String NAME = "BaseApprovalHistoryRepository";
}
