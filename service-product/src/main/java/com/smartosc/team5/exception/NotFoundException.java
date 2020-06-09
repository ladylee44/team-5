package com.smartosc.team5.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * team5
 *
 * @author Huupd
 * @created_at 09/06/2020 - 10:45 AM
 * @created_by Huupd
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
