package com.korom.filefinder.dto;

import com.korom.filefinder.dto.jpa.QueryHistory;

import java.util.List;

public class HistoryResponseDto {

    private List<QueryHistory> histories;

    public HistoryResponseDto(List<QueryHistory> histories) {
        this.histories = histories;
    }

    public List<QueryHistory> getHistories() {
        return histories;
    }

    public void setHistories(List<QueryHistory> histories) {
        this.histories = histories;
    }
}
