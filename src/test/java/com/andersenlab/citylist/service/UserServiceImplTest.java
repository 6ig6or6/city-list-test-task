package com.andersenlab.citylist.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.andersenlab.citylist.dto.UserDto;
import com.andersenlab.citylist.entity.user.UserEntity;
import com.andersenlab.citylist.exception.UserExistsException;
import com.andersenlab.citylist.repository.UserRepository;
import com.andersenlab.citylist.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void loadUserByUsername_whenUserExists_shouldReturnUser() {
        UserEntity user = new UserEntity();
        user.setUsername("username");
        when(userRepository.findByUsername("username")).thenReturn(user);

        UserDetails result = userService.loadUserByUsername("username");

        assertThat(result).isEqualTo(user);
    }

    @Test
    void loadUserByUsername_whenUserNotExists_shouldThrowException() {
        when(userRepository.findByUsername("username")).thenReturn(null);

        assertThatExceptionOfType(UsernameNotFoundException.class)
                .isThrownBy(() -> userService.loadUserByUsername("username"));
    }

    @Test
    void registerUser_whenUserExists_shouldThrowException() {
        UserDto userDto = new UserDto();
        userDto.setUsername("username");
        when(userRepository.existsByUsername("username")).thenReturn(true);

        assertThatExceptionOfType(UserExistsException.class)
                .isThrownBy(() -> userService.registerUser(userDto));
    }

    @Test
    void registerUser_whenUserNotExists_shouldReturnUser() {
        UserDto userDto = new UserDto();
        userDto.setUsername("username");
        userDto.setPassword("password");
        when(userRepository.existsByUsername("username")).thenReturn(false);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        UserEntity user = new UserEntity();
        user.setUsername("username");
        user.setPassword("encodedPassword");
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        UserEntity result = userService.registerUser(userDto);

        assertThat(result).isEqualTo(user);
    }
}