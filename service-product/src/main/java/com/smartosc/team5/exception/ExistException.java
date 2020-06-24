package com.smartosc.team5.exception;

/**
 * team5
 *
 * @author ThaoPhuong
 * @created_at 22/06/2020 - 02:08 PM
 * @created_by ThaoPhuong
 * @since 22/06/2020
 */
public class ExistException extends RuntimeException {
    public ExistException(String message) {
        super(message);
    }
}
