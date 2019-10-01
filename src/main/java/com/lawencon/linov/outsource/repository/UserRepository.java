package com.lawencon.linov.outsource.repository;

import com.lawencon.linov.outsource.model.authentication.User;
import com.lawencon.linov.outsource.util.RoleName;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    List<User> findByIdIn(List<Long> userIds);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    List<User> findAllByRoles(RoleName roleName);
}
