package com.ups.pet.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Pet Not Found")
public class PetNotFoundException extends RuntimeException{

    public PetNotFoundException() {
    }

    public PetNotFoundException(String message) {
        super(message);
    }
}