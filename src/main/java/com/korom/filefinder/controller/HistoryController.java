package com.korom.filefinder.controller;

import com.korom.filefinder.dto.jpa.service.QueryHistoryDaoService;
import com.korom.filefinder.dto.HistoryResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HistoryController {

    private final static String GET_HISTORY_ENDPOINT="/history";
    Logger log = LoggerFactory.getLogger(this.getClass());
    private final QueryHistoryDaoService queryHistoryDaoService;

    @Autowired
    public HistoryController(QueryHistoryDaoService queryHistoryDaoService) {
        this.queryHistoryDaoService = queryHistoryDaoService;
    }

    @RequestMapping(value = GET_HISTORY_ENDPOINT+"/{username}", method = RequestMethod.GET)
    public ResponseEntity<HistoryResponseDto> getHistory(@PathVariable String username) {
        log.info("Incoming request to get historical data.");
        HistoryResponseDto historyResponseDto = new HistoryResponseDto(queryHistoryDaoService.findHistoryByUsername(username));
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(historyResponseDto);
    }
}
