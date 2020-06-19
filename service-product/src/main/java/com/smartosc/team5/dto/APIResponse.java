package com.smartosc.team5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * team5
 *
 * @author Huupd
 * @created_at 19/06/2020 - 10:08 AM
 * @created_by Huupd
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class APIResponse<T> {
    private int status;
    private String message;
    private T data;
}
