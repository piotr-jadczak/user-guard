package com.pj.userguard.user.repository;

import com.pj.userguard.user.entity.Role;
import com.pj.userguard.user.entity.RoleName;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

    @Query(value = "SELECT r FROM Role r WHERE r.name = :roleName")
    Optional<Role> getByRoleName(@Param("roleName") RoleName roleName);
}
