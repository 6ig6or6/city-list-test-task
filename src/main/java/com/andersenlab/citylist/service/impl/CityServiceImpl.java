package com.andersenlab.citylist.service.impl;

import com.andersenlab.citylist.dto.CityDto;
import com.andersenlab.citylist.repository.CityRepository;
import com.andersenlab.citylist.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    @Override
    public Page<CityDto> findAllCities(int page, int size) {
        cityRepository.findAll(PageRequest.of(page, size));
        return null;
    }

    @Override
    public Page<CityDto> findCitiesByName(String name, int page, int size) {
        return null;
    }

    @Override
    public CityDto updateCityById(Long id, CityDto city) {
        return null;
    }
}
