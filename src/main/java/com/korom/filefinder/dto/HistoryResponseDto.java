package com.korom.filefinder.dto;

import com.korom.filefinder.dto.jpa.QueryHistory;

import java.util.List;

/**
 * A Java representation of the historical data.
 * Basically, it is a list of {@link QueryHistory}, because we expose what we save.
 */
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
