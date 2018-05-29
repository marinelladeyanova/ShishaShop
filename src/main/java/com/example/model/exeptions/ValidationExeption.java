package com.example.model.exeptions;

public class ValidationExeption extends Throwable {
    public ValidationExeption() {
    }

    public ValidationExeption(String message) {
        super(message);
    }

    public ValidationExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationExeption(Throwable cause) {
        super(cause);
    }

    public ValidationExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
