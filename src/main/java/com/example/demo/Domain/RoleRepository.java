package com.example.demo.Domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRoleName(String roleName);
    List<Role> findAllByRoleNameAndRoleType(String roleName,String roleType);
}