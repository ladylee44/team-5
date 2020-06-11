package com.smartosc.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * team5
 *
 * @author Huupd
 * @created_at 11/06/2020 - 11:22 AM
 * @created_by Huupd
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    private Long userId;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String userName;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

}
