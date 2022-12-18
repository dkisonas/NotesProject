package com.jk.notes.repository;


import com.jk.notes.model.user.role.ERole;
import com.jk.notes.model.user.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
