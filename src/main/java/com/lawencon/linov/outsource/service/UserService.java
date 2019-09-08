package com.lawencon.linov.outsource.service;

import com.lawencon.linov.outsource.model.authentication.User;

public interface UserService {

    Boolean checkUserByUsername(String username);
    Boolean checkUserByEmail(String email);
    User crateUser(User user);
}
