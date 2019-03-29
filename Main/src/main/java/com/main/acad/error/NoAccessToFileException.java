package com.main.acad.error;

public class NoAccessToFileException extends RuntimeException {
    public NoAccessToFileException(String message) {
        super(message);
    }

}

