package com.main.acad.error;

public class ControllerFailedException extends RuntimeException {
    public ControllerFailedException(String message) {
        super(message);
    }
}

