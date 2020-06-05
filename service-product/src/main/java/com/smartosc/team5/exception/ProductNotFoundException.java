package com.smartosc.team5.exception;

/**
 * team5
 *
 * @author ThaoPhuong
 * @created_at 05/06/2020 - 03:45 PM
 * @created_by ThaoPhuong
 * @since 05/06/2020
 */
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Integer id){
        super("Product id " + id + " not found");
    }
}
