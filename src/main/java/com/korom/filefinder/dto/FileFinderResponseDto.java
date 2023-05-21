package com.korom.filefinder.dto;

import java.util.List;
import java.util.Objects;

/**
 * A POJO Java representation of the file query response.
 * It contains the result of find files query.
 */
public class FileFinderResponseDto {

    private List<String> files;

    public FileFinderResponseDto(List<String> files) {
        this.files = files;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileFinderResponseDto that = (FileFinderResponseDto) o;
        return Objects.equals(files, that.files);
    }

    @Override
    public int hashCode() {
        return Objects.hash(files);
    }
}
