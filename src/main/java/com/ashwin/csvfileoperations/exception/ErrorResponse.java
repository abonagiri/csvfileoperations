package com.ashwin.csvfileoperations.exception;

public class ErrorResponse {

    private String error;
    private ErrorCodes errorCode;
    private Integer status;

    public String getError() {
        return error;
    }

    public ErrorCodes getErrorCode() {
        return errorCode;
    }

    public Integer getStatus() {
        return status;
    }

    public ErrorResponse(String error, ErrorCodes errorCode, Integer status) {
        this.error = error;
        this.errorCode = errorCode;
        this.status = status;
    }

    public enum ErrorCodes {
        FILE_ERROR(100), DATA_ERROR(500), PROCESSING_ERROR(1000);

        private int errorCode;

        ErrorCodes(int errorCode) {
            this.errorCode = errorCode;
        }

        public int getErrorCode() {
            return errorCode;
        }
    }
}
