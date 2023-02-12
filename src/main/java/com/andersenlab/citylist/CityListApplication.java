package com.andersenlab.citylist;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Cities API",
        version = "1.0",
        description = "Cities list app"))
@SpringBootApplication
public class CityListApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityListApplication.class, args);
    }

}
