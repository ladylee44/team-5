package com.smartosc.user.controllers;

import com.smartosc.user.dto.RoleDTO;
import com.smartosc.user.entities.RoleName;
import com.smartosc.user.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * team5
 *
 * @author Huupd
 * @created_at 11/06/2020 - 1:52 PM
 * @created_by Huupd
 */
@RestController
@RequestMapping("api/role")
public class RoleController {
    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<RoleDTO> getRole(@Valid @RequestBody RoleName roleName) {
        RoleDTO roleDTO = roleService.getRoleByName(roleName);
        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }
}
