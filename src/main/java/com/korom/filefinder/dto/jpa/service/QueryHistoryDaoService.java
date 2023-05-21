package com.korom.filefinder.dto.jpa.service;

import com.korom.filefinder.dto.jpa.QueryHistory;

import java.util.List;

/**
 * The high level functionalities of the persistence layer (historical data).
 */
public interface QueryHistoryDaoService {

    QueryHistory save(QueryHistory queryHistory);
    List<QueryHistory> findHistoryByUsername(String username);
}
