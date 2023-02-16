package com.andersenlab.citylist.security;

import com.andersenlab.citylist.controller.CityController;
import com.andersenlab.citylist.controller.UserController;
import com.andersenlab.citylist.dto.UserDto;
import com.andersenlab.citylist.mapper.UserMapper;
import com.andersenlab.citylist.service.CityService;
import com.andersenlab.citylist.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({CityController.class, UserController.class})
class SecurityConfigTest {
    private static final String CITY_JSON = "{\"id\": 1, \"name\": \"New York\"}";
    private static final String USER_JSON = "{\"username\": \"mail@mail.com\"," +
                                            "\"password\": \"password123\"}";
    private static final String CITIES_PATH = "/cities";
    private static final String USERS_PATH = "/users";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private CityService cityService;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private UserService userService;

    @Test
    void whenSendingUnauthorizedRequestToGetCities_thenSuccess() throws Exception {
        mockMvc.perform(get(CITIES_PATH))
                .andExpect(status().isOk());
    }

    @WithMockUser(authorities = "ROLE_ALLOWED_EDIT")
    @Test
    void whenSendingRequestWithAllowedEditRole_thenSuccess() throws Exception {
        mockMvc.perform(put(CITIES_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CITY_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser(authorities = "ROLE_USER")
    @Test
    void whenSendingRequestWithRoleUser_thenForbidden() throws Exception {
        mockMvc.perform(put(CITIES_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CITY_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    void whenSendingUnauthorizedRequestToRegisterUser_thenSuccess() throws Exception {
        when(userMapper.toUserDto(any())).thenReturn(new UserDto());

        mockMvc.perform(post(USERS_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(USER_JSON))
                .andExpect(status().isOk());
    }
}
