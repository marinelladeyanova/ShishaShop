package com.example.model.exeptions;

public class DiscountException extends Throwable {
    public DiscountException() {
    }

    public DiscountException(String message) {
        super(message);
    }

    public DiscountException(String message, Throwable cause) {
        super(message, cause);
    }

    public DiscountException(Throwable cause) {
        super(cause);
    }

    public DiscountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
