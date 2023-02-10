package com.andersenlab.citylist.service;

import com.andersenlab.citylist.dto.CityDto;
import org.springframework.data.domain.Page;

public interface CityService {

    Page<CityDto> findAllCities(int page, int size);

    Page<CityDto> findCitiesByName(String name, int page, int size);

    CityDto updateCityById(Long id, CityDto updatedCity);

    CityDto findById(Long id);
}
