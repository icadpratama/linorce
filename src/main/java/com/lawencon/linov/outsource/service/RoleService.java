package com.lawencon.linov.outsource.service;

import com.lawencon.linov.outsource.model.authentication.Role;
import com.lawencon.linov.outsource.util.RoleName;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findRole(RoleName name);
}
