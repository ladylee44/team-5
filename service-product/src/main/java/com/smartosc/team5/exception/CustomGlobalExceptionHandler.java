package com.smartosc.team5.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // error handle for @Valid
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, String> errorMessageMap = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(e -> {
            if (e instanceof FieldError) {
                errorMessageMap.put(((FieldError) e).getField(), e.getDefaultMessage());
            }
        });

        ErrorObject errorObject = new ErrorObject();
        errorObject.setMessages(errorMessageMap);

        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);

    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    public void constraintViolationException(HttpServletResponse response) throws IOException {
//        response.sendError(HttpStatus.BAD_REQUEST.value());
//    }

}