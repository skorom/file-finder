package com.korom.filefinder.service.archive;

import com.korom.filefinder.configuration.ApplicationConfig;
import com.korom.filefinder.dto.jpa.QueriedFile;
import com.korom.filefinder.dto.jpa.QueryHistory;
import com.korom.filefinder.dto.jpa.service.QueryHistoryDaoService;
import com.korom.filefinder.dto.FileFinderResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * An implementation of the archiving functionality.
 */
@Service
public class QueryHistorySaverServiceImpl implements QueryHistorySaverService{

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final QueryHistoryDaoService queryHistoryDaoService;
    private final ApplicationConfig applicationConfig;

    @Autowired
    public QueryHistorySaverServiceImpl(QueryHistoryDaoService queryHistoryDaoService, ApplicationConfig applicationConfig) {
        this.queryHistoryDaoService = queryHistoryDaoService;
        this.applicationConfig = applicationConfig;
    }

    @Override
    public QueryHistory archiveQuery(FileFinderResponseDto fileFinderResponseDto) {
        QueryHistory queryHistory = new QueryHistory();
        queryHistory.setExecuted(LocalDateTime.now());
        queryHistory.setUsername(applicationConfig.getUsername());
        queryHistory.setFilePaths(fileFinderResponseDto.getFiles().stream().map(QueriedFile::new).collect(Collectors.toSet()));
        QueryHistory savedQuery = queryHistoryDaoService.save(queryHistory);
        if(savedQuery != null && savedQuery.getId() != null){
            log.info("Query from {} successfully archived.",applicationConfig.getUsername());
        }else{
            log.error("The following query could not be saved: {}", queryHistory);
        }

        return savedQuery;
    }
}
