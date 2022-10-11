package com.api.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.backend.enums.RoleName;
import com.api.backend.models.RoleModel;
import com.api.backend.repositories.RoleRepository;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleService {
    @Autowired
    final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleRepository getRoleRepository() {
        return this.roleRepository;
    }

    public Optional<RoleModel> findByRoleName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName);
    };

    public Optional<RoleModel> findById(UUID id) {
        return roleRepository.findById(id);
    }
}
