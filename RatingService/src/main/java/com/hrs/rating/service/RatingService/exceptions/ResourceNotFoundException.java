package com.hrs.rating.service.RatingService.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(){
        super("Resource Not found on the server");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
