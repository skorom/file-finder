package com.korom.filefinder.exception;

public class FileFinderException extends RuntimeException{

    private final String errorCode;

    public FileFinderException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
