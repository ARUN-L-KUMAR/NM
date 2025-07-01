package com.example.custorder.Exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException (String Message) {
        super(Message);
    }
}
