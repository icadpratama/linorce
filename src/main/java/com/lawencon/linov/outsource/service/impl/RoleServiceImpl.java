package com.lawencon.linov.outsource.service.impl;

import com.lawencon.linov.outsource.model.authentication.Role;
import com.lawencon.linov.outsource.repository.RoleRepository;
import com.lawencon.linov.outsource.service.RoleService;
import com.lawencon.linov.outsource.util.RoleName;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> setRole(RoleName name) {
        return roleRepository.findByName(name);
    }
}
