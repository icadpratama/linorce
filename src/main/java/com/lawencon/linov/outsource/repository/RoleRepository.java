package com.lawencon.linov.outsource.repository;

import com.lawencon.linov.outsource.model.authentication.Role;
import com.lawencon.linov.outsource.util.RoleName;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
