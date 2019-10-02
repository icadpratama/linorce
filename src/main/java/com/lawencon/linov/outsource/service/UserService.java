package com.lawencon.linov.outsource.service;

import com.lawencon.linov.outsource.model.authentication.Role;
import com.lawencon.linov.outsource.model.authentication.User;
import com.lawencon.linov.outsource.util.RoleName;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Boolean checkUserByUsername(String username);
    Boolean checkUserByEmail(String email);
    User crateUser(User user);
    Optional<User> userListByUsername(String username);
    List<User> listHr(Role role);
    Optional<User> userById(Long id);
}
