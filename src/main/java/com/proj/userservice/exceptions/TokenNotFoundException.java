package com.proj.userservice.exceptions;

public class TokenNotFoundException extends Exception{
    public TokenNotFoundException(String message){
        super(message);
    }
}
