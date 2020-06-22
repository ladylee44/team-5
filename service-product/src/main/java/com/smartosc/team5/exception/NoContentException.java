package com.smartosc.team5.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * team5
 *
 * @author ThaoPhuong
 * @created_at 12/06/2020 - 03:01 PM
 * @created_by ThaoPhuong
 * @since 12/06/2020
 */

public class NoContentException extends RuntimeException{
    public NoContentException(String message) {
        super(message);
    }
}
