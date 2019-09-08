package com.lawencon.linov.outsource.service;

import com.lawencon.linov.outsource.model.authentication.User;

import java.util.Optional;

public interface UserService {

    Boolean checkUserByUsername(String username);
    Boolean checkUserByEmail(String email);
    User crateUser(User user);
    Optional<User> userListByUsername(String username);
}
