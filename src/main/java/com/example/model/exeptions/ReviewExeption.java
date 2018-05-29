package com.example.model.exeptions;

public class ReviewExeption extends Throwable {
    public ReviewExeption() {
    }

    public ReviewExeption(String message) {
        super(message);
    }

    public ReviewExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public ReviewExeption(Throwable cause) {
        super(cause);
    }

    public ReviewExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
