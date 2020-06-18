package com.smartosc.user.convert;

import com.smartosc.user.dto.RoleDTO;
import com.smartosc.user.entities.Role;

/**
 * team5
 *
 * @author Huupd
 * @created_at 11/06/2020 - 11:42 AM
 * @created_by Huupd
 */
public class RoleConvert {
    private RoleConvert(){}

    public static RoleDTO convertRoleDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }
}
