package com.korom.filefinder.dto;

public class ErrorResponse {

    private String message;
    private String errorCode;
    private String cause;


    public ErrorResponse(String message, String errorCode, String cause) {
        this.message = message;
        this.errorCode = errorCode;
        this.cause = cause;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
