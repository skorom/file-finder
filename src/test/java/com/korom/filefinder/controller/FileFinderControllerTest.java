package com.korom.filefinder.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korom.filefinder.dto.FileFinderResponseDto;
import com.korom.filefinder.service.archive.QueryHistorySaverService;
import com.korom.filefinder.service.finder.FileFinderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class FileFinderControllerTest {
    @Mock
    QueryHistorySaverService queryHistorySaverService;
    @Mock
    private FileFinderService fileFinderService;
    @InjectMocks
    private FileFinderController fileFinderController;
    private final ObjectMapper mapper = new ObjectMapper();
    private MockMvc mockMvc;
    private FileFinderResponseDto fileFinderResponseDto;

    @BeforeEach
    void setUp(){
        fileFinderResponseDto = new FileFinderResponseDto(List.of("a.txt","b.txt","c.txt"));
        mockMvc = MockMvcBuilders
                .standaloneSetup(fileFinderController)
                .setControllerAdvice(CommonController.class)
                .alwaysDo(print()).build();
        when(fileFinderService.findUniqueFilesByFileExtension(any())).thenReturn(fileFinderResponseDto.getFiles().stream().map(Paths::get).toList());
    }

    @Test
    @DisplayName("Unique file finder called on request.")
    void requestOnFindFileEndpointIndicatesAUniqueFileSearchMechanism() throws Exception {
        mockMvc.perform(get(FileFinderController.FIND_FILE_ENDPOINT+"/dummy")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(fileFinderResponseDto)));
    }

    @Test
    @DisplayName("Archive the finding query.")
    void archiveTheQueryWhenFindingEndpointCalled() throws Exception {
        mockMvc.perform(get(FileFinderController.FIND_FILE_ENDPOINT+"/dummy")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(fileFinderResponseDto)));
        ArgumentCaptor<FileFinderResponseDto> archiveCaptor = ArgumentCaptor.forClass(FileFinderResponseDto.class);
        verify(queryHistorySaverService).archiveQuery(archiveCaptor.capture());
        assertEquals(fileFinderResponseDto,archiveCaptor.getValue());
    }

}