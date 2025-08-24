package com.training.Dto;

import org.springframework.http.HttpStatus;

public class ServerResponse {
    private String message;
    private HttpStatus status;

    public ServerResponse() {
    }

    public ServerResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
