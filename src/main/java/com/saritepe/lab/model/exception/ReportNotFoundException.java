package com.saritepe.lab.model.exception;

import com.saritepe.lab.model.entity.Report;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReportNotFoundException extends RuntimeException{
    public ReportNotFoundException(String message) {
        super(message);
    }
}
