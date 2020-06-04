package com.smartosc.user.repositories;

import com.smartosc.user.entities.Role;
import com.smartosc.user.entities.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Team5HuuAndThao
 *
 * @author Huupd
 * @created_at 03/06/2020 - 10:49 AM
 * @created_by Huupd
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleName roleName);
}

