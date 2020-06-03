package com.smartosc.user.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Team5HuuAndThao
 *
 * @author Huupd
 * @created_at 03/06/2020 - 1:44 PM
 * @created_by Huupd
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestSignup{

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Size(max = 60)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}