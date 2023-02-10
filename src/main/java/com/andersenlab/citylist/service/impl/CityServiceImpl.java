package com.andersenlab.citylist.service.impl;

import com.andersenlab.citylist.dto.CityDto;
import com.andersenlab.citylist.exception.EntityNotFoundException;
import com.andersenlab.citylist.mapper.CityMapper;
import com.andersenlab.citylist.repository.CityRepository;
import com.andersenlab.citylist.service.CityService;
import com.andersenlab.citylist.util.EntityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public Page<CityDto> findAllCities(int page, int size) {
        return this.cityRepository.findAll(PageRequest.of(page, size))
                .map(cityMapper::toCityDto);
    }

    @Override
    public Page<CityDto> findCitiesByName(String name, int page, int size) {
        return this.cityRepository.findAllByName(name, PageRequest.of(page, size))
                .map(cityMapper::toCityDto);
    }

    @Override
    public CityDto updateCityById(Long id, CityDto updatedCity) {
        CityDto existingCity = this.findById(id);
        if (!existingCity.equals(updatedCity)) {
            EntityUtils.patchEntity(updatedCity, existingCity, "id");
            existingCity = this.cityMapper.toCityDto(this.cityRepository.save(this.cityMapper.toCityEntity(existingCity)));
        }
        return existingCity;
    }

    @Override
    public CityDto findById(Long id) {
        return this.cityMapper.toCityDto(cityRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new));
    }

}
