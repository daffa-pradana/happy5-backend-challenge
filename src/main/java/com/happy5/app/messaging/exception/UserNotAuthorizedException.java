package com.happy5.app.messaging.exception;

public class UserNotAuthorizedException extends RuntimeException {
    public UserNotAuthorizedException(String msg) {
        super(msg);
    }
}
