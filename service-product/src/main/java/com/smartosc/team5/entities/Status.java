package com.smartosc.team5.entities;

/**
 * Team5
 *
 * @author Huupd
 * @created_at 04/06/2020 - 3:00 PM
 * @created_by Huupd
 */
public enum Status {
    ACTIVE(1),
    INACTIVE(0);
    private final int value;

    Status(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
