package com.lawencon.linov.outsource.service.impl;

import com.lawencon.linov.outsource.repository.UserRepository;
import com.lawencon.linov.outsource.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Boolean checkUserByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean checkUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
