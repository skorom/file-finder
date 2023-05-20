package com.korom.filefinder.service.archive;

import com.korom.filefinder.dto.jpa.QueryHistory;
import com.korom.filefinder.dto.FileFinderResponseDto;

public interface QueryHistorySaverService {
    QueryHistory archiveQuery(FileFinderResponseDto fileFinderResponseDto);
}
