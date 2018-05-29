package com.example.model.exeptions;

public class DatabaseExepton extends Throwable {

    public DatabaseExepton() {
    }

    public DatabaseExepton(String message) {
        super(message);
    }

    public DatabaseExepton(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseExepton(Throwable cause) {
        super(cause);
    }

    public DatabaseExepton(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
