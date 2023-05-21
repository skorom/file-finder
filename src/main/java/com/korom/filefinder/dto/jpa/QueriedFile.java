package com.korom.filefinder.dto.jpa;


import javax.persistence.Embeddable;

/**
 * The model class is part of the JAVA representation of the historical data.
 * This particular class stores 1 element of a query result and will be embedded into {@link QueryHistory}
 */
@Embeddable
public class QueriedFile {
    private String filePath;

    public QueriedFile() {
    }

    public QueriedFile(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
