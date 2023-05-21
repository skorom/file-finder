package com.korom.filefinder.exception;

/**
 * A custom exception type especially for this microservice.
 * The exception is extended with an error code field.
 */
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
