package com.main.acad.error;

public class ConnectionPoolFailedException extends RuntimeException {
    public ConnectionPoolFailedException(String message) {
        super(message);
    }
}
