package com.andersenlab.citylist.controller.impl;

import com.andersenlab.citylist.controller.CityController;
import com.andersenlab.citylist.dto.CityDto;
import com.andersenlab.citylist.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CityControllerImpl implements CityController {

    private final CityService cityService;

    @Override
    public ResponseEntity<Page<CityDto>> getCities(final int page, final int size) {
        log.info("Received GET cities request");

        Page<CityDto> citiesPage = cityService.findAllCities(page, size);
        return new ResponseEntity<>(citiesPage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<CityDto>> getCitiesByName(final String name,
                                                         final int page,
                                                         final int size) {
        log.info("Received GET request to find cities with name {}", name);

        Page<CityDto> citiesPage = cityService.findCitiesByName(name, page, size);
        return new ResponseEntity<>(citiesPage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CityDto> updateCityById(final CityDto cityDto) {
        final Long id = cityDto.getId();

        log.info("Received PUT request to update city with id {}", id);

        return new ResponseEntity<>(cityService.updateCityById(id, cityDto), HttpStatus.OK);
    }
}
