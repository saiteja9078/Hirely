package com.sai.hirely.exceptions.file;


public class InvalidFileException extends RuntimeException{
    public InvalidFileException(String message) {
        super(message);
    }
    public InvalidFileException(String message,String message2) {
        super(message+" "+message2);
    }
}
