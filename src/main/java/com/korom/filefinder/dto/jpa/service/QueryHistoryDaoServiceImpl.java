package com.korom.filefinder.dto.jpa.service;

import com.korom.filefinder.dto.jpa.QueryHistory;
import com.korom.filefinder.dto.jpa.repo.QueryHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service is using {@link org.springframework.data.jpa.repository.JpaRepository} to access the historical data
 * and responsible for persisting, querying data.
 */
@Service
public class QueryHistoryDaoServiceImpl implements QueryHistoryDaoService {
    QueryHistoryRepository queryHistoryRepository;

    @Autowired
    public QueryHistoryDaoServiceImpl(QueryHistoryRepository queryHistoryRepository) {
        this.queryHistoryRepository = queryHistoryRepository;
    }

    @Override
    public QueryHistory save(QueryHistory queryHistory) {
        return queryHistoryRepository.save(queryHistory);
    }

    @Override
    public List<QueryHistory> findHistoryByUsername(String username) {
        return queryHistoryRepository.findByUsername(username);
    }
}
