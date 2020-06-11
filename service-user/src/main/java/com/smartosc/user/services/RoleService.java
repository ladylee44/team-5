package com.smartosc.user.services;

import com.smartosc.user.convert.RoleConvert;
import com.smartosc.user.dto.RoleDTO;
import com.smartosc.user.entities.Role;
import com.smartosc.user.entities.RoleName;
import com.smartosc.user.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * team5
 *
 * @author Huupd
 * @created_at 11/06/2020 - 11:40 AM
 * @created_by Huupd
 */
@Service
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleDTO getRoleByName(RoleName roleName){
        Optional<Role> role = roleRepository.findByName(roleName);
        if (role.isPresent()){
            return RoleConvert.convertRoleDTO(role.get());
        }
        return null;
    }

}
