package com.korom.filefinder.service.archive;

import com.korom.filefinder.configuration.ApplicationConfig;
import com.korom.filefinder.dto.jpa.QueriedFile;
import com.korom.filefinder.dto.jpa.QueryHistory;
import com.korom.filefinder.dto.jpa.service.QueryHistoryDaoService;
import com.korom.filefinder.dto.FileFinderResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QueryHistorySaverServiceImplTest {

    private static final String DUMMY_USER="dummyUserName";
    @Mock
    ApplicationConfig applicationConfig;
    @Mock
    QueryHistoryDaoService queryHistoryDaoService;

    @InjectMocks
    QueryHistorySaverServiceImpl queryHistorySaverService;

    @BeforeEach
    void setUp() {
        when(applicationConfig.getUsername()).thenReturn(DUMMY_USER);
    }

    @Test
    void archiveCallsRepository() {
        ArgumentCaptor<QueryHistory> historyArgumentCaptor = ArgumentCaptor.forClass(QueryHistory.class);
        FileFinderResponseDto fileFinderResponseDto = new FileFinderResponseDto(List.of("a.txt","b.txt","c.txt"));
        queryHistorySaverService.archiveQuery(fileFinderResponseDto);
        verify(queryHistoryDaoService).save(historyArgumentCaptor.capture());
        QueryHistory actualQueryHistory = historyArgumentCaptor.getValue();
        FileFinderResponseDto actualFileFinderResponse = new FileFinderResponseDto(actualQueryHistory.getFilePaths().stream().map(QueriedFile::getFilePath).toList());
        assertAll(
                ()-> assertEquals(DUMMY_USER,actualQueryHistory.getUsername()),
                ()-> assertNotNull(actualQueryHistory.getExecuted()),
                ()-> assertThat(fileFinderResponseDto.getFiles()).hasSameElementsAs(actualFileFinderResponse.getFiles())
        );
    }
}