package com.andersenlab.citylist.controller;

import com.andersenlab.citylist.dto.CityDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/cities")
public interface CityController {
    @GetMapping
    ResponseEntity<Page<CityDto>> getCities(@RequestParam(required = false, defaultValue = "0") final int page,
                                            @RequestParam(required = false, defaultValue = "6") final int size);

    @GetMapping("/{name}")
    ResponseEntity<Page<CityDto>> getCitiesByName(@PathVariable final String name,
                                                  @RequestParam(required = false, defaultValue = "0") final int page,
                                                  @RequestParam(required = false, defaultValue = "6") final int size);

    @PutMapping("/{id}")
    ResponseEntity<CityDto> updateCityById(@PathVariable final Long id, @RequestBody final CityDto cityDto);
}
