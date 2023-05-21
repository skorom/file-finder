package com.korom.filefinder.service.archive;

import com.korom.filefinder.dto.jpa.QueryHistory;
import com.korom.filefinder.dto.FileFinderResponseDto;

/**
 * One of the two high level features of the microservice: gather some historical data about the executed queries.
 */
public interface QueryHistorySaverService {
    QueryHistory archiveQuery(FileFinderResponseDto fileFinderResponseDto);
}
