package com.korom.filefinder.dto.jpa.service;

import com.korom.filefinder.dto.jpa.QueryHistory;

import java.util.List;

public interface QueryHistoryDaoService {

    QueryHistory save(QueryHistory queryHistory);
    List<QueryHistory> findHistoryByUsername(String username);
}
