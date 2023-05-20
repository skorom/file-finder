package com.korom.filefinder.dto.jpa;


import javax.persistence.Embeddable;

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
