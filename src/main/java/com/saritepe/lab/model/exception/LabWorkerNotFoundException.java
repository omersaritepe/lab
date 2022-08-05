package com.saritepe.lab.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LabWorkerNotFoundException extends RuntimeException{

    public LabWorkerNotFoundException() {
    }

    public LabWorkerNotFoundException(String message) {
        super(message);
    }
}
