package com.smartosc.user.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Team5HuuAndThao
 *
 * @author Huupd
 * @created_at 03/06/2020 - 11:18 AM
 * @created_by Huupd
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestLogin {

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
}
