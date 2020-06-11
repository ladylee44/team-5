package com.smartosc.user.services;

import com.smartosc.user.convert.UserConvert;
import com.smartosc.user.dto.UserDTO;
import com.smartosc.user.entities.User;
import com.smartosc.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * team5
 *
 * @author Huupd
 * @created_at 11/06/2020 - 11:17 AM
 * @created_by Huupd
 */
@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserDTO loadUserByUsername(String username){
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()){
            return UserConvert.convertUserToUserDTO(userOptional.get());
        }
        return null;
    }
}
