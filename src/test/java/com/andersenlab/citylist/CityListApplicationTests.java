package com.andersenlab.citylist;

import com.andersenlab.citylist.controller.CityController;
import com.andersenlab.citylist.controller.UserController;
import com.andersenlab.citylist.repository.CityRepository;
import com.andersenlab.citylist.repository.UserRepository;
import com.andersenlab.citylist.service.CityService;
import com.andersenlab.citylist.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class CityListApplicationTests {
    private final CityController cityController;
    private final UserController userController;
    private final CityRepository cityRepository;
    private final UserRepository userRepository;
    private final CityService cityService;
    private final UserService userService;

    @Autowired
    CityListApplicationTests(CityController cityController,
                             UserController userController,
                             CityRepository cityRepository,
                             UserRepository userRepository,
                             CityService cityService,
                             UserService userService) {
        this.cityController = cityController;
        this.userController = userController;
        this.cityRepository = cityRepository;
        this.userRepository = userRepository;
        this.cityService = cityService;
        this.userService = userService;
    }

    @Test
    void whenContextLoadsSuccess() {
        assertThat(cityController).isNotNull();
        assertThat(userController).isNotNull();
        assertThat(cityRepository).isNotNull();
        assertThat(userRepository).isNotNull();
        assertThat(cityService).isNotNull();
        assertThat(userService).isNotNull();
    }

}
