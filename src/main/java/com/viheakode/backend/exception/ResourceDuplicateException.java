package com.viheakode.backend.exception;

public class ResourceDuplicateException extends RuntimeException{
    public ResourceDuplicateException(String message) {
        super(message);
    }
}
