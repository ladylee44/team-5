package com.smartosc.user.convert;

import com.smartosc.user.dto.UserDTO;
import com.smartosc.user.entities.User;

/**
 * team5
 *
 * @author Huupd
 * @created_at 11/06/2020 - 11:24 AM
 * @created_by Huupd
 */
public class UserConvert {
    public static UserDTO convertUserToUserDTO(User user){
        UserDTO userDTO =  new UserDTO();
        userDTO.setUserId(user.getId());
        userDTO.setUserName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        return   userDTO;
    }
}
