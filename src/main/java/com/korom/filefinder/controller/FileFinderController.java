package com.korom.filefinder.controller;

import com.korom.filefinder.dto.FileFinderResponseDto;
import com.korom.filefinder.service.archive.QueryHistorySaverService;
import com.korom.filefinder.service.finder.FileFinderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.stream.Collectors;

@RestController
public class FileFinderController {

    public static final String FIND_FILE_ENDPOINT="/findfile";
    private final FileFinderService fileFinderService;
    private final QueryHistorySaverService queryHistorySaverService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public FileFinderController(FileFinderService fileFinderService, QueryHistorySaverService queryHistorySaverService) {
        this.fileFinderService = fileFinderService;
        this.queryHistorySaverService=queryHistorySaverService;
    }


    @RequestMapping(value=FIND_FILE_ENDPOINT+"/{fileExtension}", method = RequestMethod.GET)
    public ResponseEntity<FileFinderResponseDto> findFilesByExtension(@PathVariable String fileExtension){
        log.info("Incoming request to find the files with the following extension: {}",fileExtension);
        FileFinderResponseDto fileFinderResponseDto = new FileFinderResponseDto(fileFinderService.findUniqueFilesByFileExtension(fileExtension).stream().map(Path::toString).collect(Collectors.toList()));
        queryHistorySaverService.archiveQuery(fileFinderResponseDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(fileFinderResponseDto);
    }
}
