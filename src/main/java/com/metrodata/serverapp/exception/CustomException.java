package com.metrodata.serverapp.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {

    private String code; // REGION_NOT_FOUND
    private int status; // 404,400,500

    public CustomException(String message, String code, int status) {
        super(message);
        this.code = code;
        this.status = status;
    }
}
