package com.lawencon.linov.outsource.service.impl;

import com.lawencon.linov.outsource.model.authentication.Role;
import com.lawencon.linov.outsource.model.authentication.User;
import com.lawencon.linov.outsource.repository.UserRepository;
import com.lawencon.linov.outsource.service.UserService;
import com.lawencon.linov.outsource.util.RoleName;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public User crateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> userListByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> listHr(Role role) {
        role.setId((long) 3);
        return userRepository.findAllByRoles(role);
    }

    @Override
    public Optional<User> userById(Long id) {
        return userRepository.findById(id);
    }
}
