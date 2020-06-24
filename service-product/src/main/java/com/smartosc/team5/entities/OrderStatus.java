package com.smartosc.team5.entities;

/**
 * team5
 *
 * @author Huupd
 * @created_at 22/06/2020 - 12:17 PM
 * @created_by Huupd
 */
public enum OrderStatus {
    ORDER_PROCESSING(0),
    ORDER_CONFIRM(1),
    ORDER_SUCCESS(2),
    ORDER_CANCLED(3);

    private final int value;

    OrderStatus(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
