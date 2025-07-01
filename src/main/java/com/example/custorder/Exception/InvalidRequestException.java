package com.example.custorder.Exception;

public class InvalidRequestException extends RuntimeException{
    public InvalidRequestException (String Message) {
        super(Message);
    }
}
