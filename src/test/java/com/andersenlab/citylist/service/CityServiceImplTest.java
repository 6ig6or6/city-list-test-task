package com.andersenlab.citylist.service;

import com.andersenlab.citylist.dto.CityDto;
import com.andersenlab.citylist.entity.CityEntity;
import com.andersenlab.citylist.mapper.CityMapper;
import com.andersenlab.citylist.repository.CityRepository;
import com.andersenlab.citylist.service.impl.CityServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {
    @Mock
    private CityRepository cityRepository;

    @Mock
    private CityMapper cityMapper;

    @InjectMocks
    private CityServiceImpl cityService;

    @Test
    void whenFindAllCities_thenCorrectResult() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        List<CityEntity> cityEntities = Arrays.asList(
                new CityEntity(1L, "City1", "photo_link_1"),
                new CityEntity(2L, "City2", "photo_link_2")
        );
        Page<CityEntity> page = new PageImpl<>(cityEntities, pageable, 2);
        when(cityRepository.findAll(pageable)).thenReturn(page);

        List<CityDto> cityDtos = Arrays.asList(
                new CityDto(1L, "City1", "photo_link_1"),
                new CityDto(2L, "City2", "photo_link_2")
        );
        when(cityMapper.toCityDto(cityEntities.get(0))).thenReturn(cityDtos.get(0));
        when(cityMapper.toCityDto(cityEntities.get(1))).thenReturn(cityDtos.get(1));

        // When
        Page<CityDto> result = cityService.findAllCities(0, 10);

        // Then
        assertThat(result.getContent()).isEqualTo(cityDtos);
    }

    @Test
    void whenFindCitiesByName_thenCorrectResult() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        String name = "City1";
        List<CityEntity> cityEntities = Collections.singletonList(
                new CityEntity(1L, name, "photo_link_1")
        );
        Page<CityEntity> page = new PageImpl<>(cityEntities, pageable, 1);
        when(cityRepository.findAllByNameIgnoreCase(name, pageable)).thenReturn(page);

        List<CityDto> cityDtos = Collections.singletonList(
                new CityDto(1L, name, "photo_link_1")
        );
        when(cityMapper.toCityDto(cityEntities.get(0))).thenReturn(cityDtos.get(0));

        // When
        Page<CityDto> result = cityService.findCitiesByName(name, 0, 10);

        // Then
        assertThat(result.getContent()).isEqualTo(cityDtos);
    }

}
