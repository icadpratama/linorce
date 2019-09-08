package com.lawencon.linov.outsource.service;

public interface UserService {

    Boolean checkUserByUsername(String username);
    Boolean checkUserByEmail(String email);
}
