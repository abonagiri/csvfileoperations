package com.ashwin.csvfileoperations.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static com.ashwin.csvfileoperations.exception.ErrorResponse.ErrorCodes;

@RestControllerAdvice
public class RestExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * Handles ValidationExceptions from the rest controller.
     *
     * @param e ValidationException
     * @return error response POJO
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ValidationException.class)
    public ErrorResponse handleValidationException(HttpServletRequest request, ValidationException e) {
        log.info(e.getMessage(), e);
        return new ErrorResponse(e.getMessage(),
                ErrorCodes.DATA_ERROR,
                HttpStatus.BAD_REQUEST.value());
    }

    /**
     * Handles JsonProcessingExceptions from the rest controller.
     *
     * @param e JsonProcessingException
     * @return error response POJO
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = JsonProcessingException.class)
    public ErrorResponse handleJsonProcessingException(HttpServletRequest request, JsonProcessingException e) {
        return new ErrorResponse(   e.getMessage(),
                ErrorCodes.DATA_ERROR,
                HttpStatus.BAD_REQUEST.value());
    }

    /**
     * Handles IllegalArgumentExceptions from the rest controller.
     *
     * @param e IllegalArgumentException
     * @return error response POJO
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ErrorResponse handleIllegalArgumentException(HttpServletRequest request, IllegalArgumentException e) {
        return new ErrorResponse( e.getMessage(),
                ErrorCodes.DATA_ERROR,
                HttpStatus.BAD_REQUEST.value());
    }

    /**
     * Handles ServiceException from the rest controller.
     *
     * @param e ServiceException
     * @return error response POJO
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = ServiceException.class)
    public ErrorResponse handleServiceException(HttpServletRequest request, ServiceException e) {
        return new ErrorResponse(   e.getMessage(),
                ErrorCodes.PROCESSING_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    /**
     * Handles all remaining exceptions from the rest controller.
     *
     * This acts as a catch-all for any exceptions not handled by previous exception handlers.
     *
     * @param e Exception
     * @return error response POJO
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ErrorResponse handleException(HttpServletRequest request, Exception e) {
        return new ErrorResponse( e.getMessage(),
                ErrorCodes.PROCESSING_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
