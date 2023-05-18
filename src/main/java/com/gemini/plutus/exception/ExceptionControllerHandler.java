package com.gemini.plutus.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerHandler {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerHandler.class);

    @ExceptionHandler
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException exception){
        logger.error("UserNotFoundException thrown by controller {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleInvalidParametersException(InvalidParametersException exception){
        logger.error("InvalidParametersException thrown by controller {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

}
