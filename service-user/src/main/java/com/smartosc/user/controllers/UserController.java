package com.smartosc.user.controllers;

import com.smartosc.user.dto.UserDTO;
import com.smartosc.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * team5
 *
 * @author Huupd
 * @created_at 11/06/2020 - 11:16 AM
 * @created_by Huupd
 */
@RestController
@RequestMapping("api/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<UserDTO> getUserByUserName(String name){
        UserDTO userDTO = userService.loadUserByUsername(name);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
