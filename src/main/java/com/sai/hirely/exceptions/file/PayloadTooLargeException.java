package com.sai.hirely.exceptions.file;

public class PayloadTooLargeException extends RuntimeException{

    public PayloadTooLargeException(String message) {
        super(message);
    }
}
