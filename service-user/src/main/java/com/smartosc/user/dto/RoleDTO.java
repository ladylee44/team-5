package com.smartosc.user.dto;

import com.smartosc.user.entities.RoleName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * team5
 *
 * @author Huupd
 * @created_at 11/06/2020 - 11:41 AM
 * @created_by Huupd
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private Long id;
    private RoleName name;
}