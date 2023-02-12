package com.andersenlab.citylist.service.impl;

import com.andersenlab.citylist.dto.UserDto;
import com.andersenlab.citylist.entity.UserEntity;
import com.andersenlab.citylist.mapper.UserMapper;
import com.andersenlab.citylist.repository.UserRepository;
import com.andersenlab.citylist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    @Override
    public UserEntity registerUser(UserDto userDto) {
        UserEntity user = userMapper.toUserEntity(userDto);
        return userRepository.save(user);
    }
}
