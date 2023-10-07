package com.pj.userguard.util.api;

public record ExceptionResponse(String message,
                                String timestamp,
                                String path,
                                String user) {

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "message='" + message + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", path='" + path + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
