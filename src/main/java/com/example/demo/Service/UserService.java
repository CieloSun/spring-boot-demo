package com.example.demo.Service;

import com.example.demo.Domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RolePermissionRepository rolePermissionRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    public User findById(Long id){
        return userRepository.findOne(id);
    }
    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findUserByEmailAndPassword(email, password);
    }
    public Set<Role> findRolesByUserId(Long userId){
        List<UserRole> userRoles=userRoleRepository.findAllByUserId(userId);
        Set<Role> roles=new HashSet<>();
        for (UserRole userRole:userRoles){
            roles.add(roleRepository.findOne(userRole.getRoleId()));
        }
        return roles;
    }
    public List<Permission> findPermissionsByRoleId(Long roleId){
        List<RolePermission> rolePermissions=rolePermissionRepository.findAllByRoleId(roleId);
        List<Permission> permissions=new ArrayList<>();
        for(RolePermission rolePermission:rolePermissions){
            permissions.add(permissionRepository.findOne(rolePermission.getPermissionId()));
        }
        return permissions;
    }
    public Set<Permission> findPermissionByUserId(Long userId){
        Set<Permission> permissions=new HashSet<>();
        Set<Role> roles=findRolesByUserId(userId);
        for(Role role:roles){
            permissions.addAll(findPermissionsByRoleId(role.getId()));
        }
        return permissions;
    }
}
