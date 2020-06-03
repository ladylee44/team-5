package com.smartosc.user.repository;

import com.smartosc.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Team5HuuAndThao
 *
 * @author Huupd
 * @created_at 03/06/2020 - 10:48 AM
 * @created_by Huupd
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
