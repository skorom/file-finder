package com.korom.filefinder.dto.jpa.repo;

import com.korom.filefinder.dto.jpa.QueryHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueryHistoryRepository extends JpaRepository<QueryHistory,Integer> {

    List<QueryHistory> findByUsername(String username);
}
