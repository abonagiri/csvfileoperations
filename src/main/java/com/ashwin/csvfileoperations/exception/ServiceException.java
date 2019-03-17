package com.ashwin.csvfileoperations.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ServiceException extends RuntimeException {

    public ServiceException(String msg) {
        super(msg);
    }
}
