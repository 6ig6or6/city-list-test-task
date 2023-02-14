package com.andersenlab.citylist;

import com.andersenlab.citylist.controller.CityController;
import com.andersenlab.citylist.controller.CsvLoaderController;
import com.andersenlab.citylist.controller.UserController;
import com.andersenlab.citylist.repository.CityRepository;
import com.andersenlab.citylist.repository.UserRepository;
import com.andersenlab.citylist.service.CityService;
import com.andersenlab.citylist.service.CsvLoaderService;
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
    private final CsvLoaderController csvLoaderController;
    private final UserController userController;
    private final CityRepository cityRepository;
    private final UserRepository userRepository;
    private final CityService cityService;
    private final UserService userService;
    private final CsvLoaderService csvLoaderService;

    @Autowired
    CityListApplicationTests(CityController cityController,
                             CsvLoaderController csvLoaderController,
                             UserController userController,
                             CityRepository cityRepository,
                             UserRepository userRepository,
                             CityService cityService,
                             UserService userService,
                             CsvLoaderService csvLoaderService) {
        this.cityController = cityController;
        this.csvLoaderController = csvLoaderController;
        this.userController = userController;
        this.cityRepository = cityRepository;
        this.userRepository = userRepository;
        this.cityService = cityService;
        this.userService = userService;
        this.csvLoaderService = csvLoaderService;
    }

    @Test
    void whenContextLoadsSuccess() {
        assertThat(cityController).isNotNull();
        assertThat(csvLoaderController).isNotNull();
        assertThat(userController).isNotNull();
        assertThat(cityRepository).isNotNull();
        assertThat(userRepository).isNotNull();
        assertThat(cityService).isNotNull();
        assertThat(userService).isNotNull();
        assertThat(csvLoaderService).isNotNull();
    }

}
