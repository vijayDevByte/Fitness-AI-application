package com.project.activity_service.ExceptionHandling;

public class ActivityNotFoundException extends RuntimeException {

    public ActivityNotFoundException(String message) {
        super(message);
    }

}
