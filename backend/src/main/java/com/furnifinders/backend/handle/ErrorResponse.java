package com.furnifinders.backend.handle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private int status;
    private String message;
    private long timeStamp;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timeStamp = System.currentTimeMillis();
    }
}
