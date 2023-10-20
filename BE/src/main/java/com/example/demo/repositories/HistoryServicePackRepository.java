package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(HistoryServicePackRepository.NAME)
public interface HistoryServicePackRepository extends JpaRepository<HistoryServicePack, String> {
    public static final String NAME = "BaseHistoryServicePackRepository";

}
