package com.andersenlab.citylist.service.impl;

import com.andersenlab.citylist.dto.UserDto;
import com.andersenlab.citylist.entity.user.Role;
import com.andersenlab.citylist.entity.user.UserEntity;
import com.andersenlab.citylist.exception.UserExistsException;
import com.andersenlab.citylist.repository.UserRepository;
import com.andersenlab.citylist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    @Override
    public UserEntity registerUser(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new UserExistsException();
        }
        UserEntity user = new UserEntity();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        this.addRoles(user);
        return userRepository.save(user);
    }

    private void addRoles(UserEntity user) {
        if (userRepository.count() == 0) {
            user.getRoles().add(Role.ROLE_ALLOWED_EDIT);
        } else {
            user.getRoles().add(Role.ROLE_USER);
        }
    }
}
