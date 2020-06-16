package com.smartosc.team5.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * team5
 *
 * @author Huupd
 * @created_at 09/06/2020 - 4:40 PM
 * @created_by Huupd
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {
    private String username;
    private String password;
}
