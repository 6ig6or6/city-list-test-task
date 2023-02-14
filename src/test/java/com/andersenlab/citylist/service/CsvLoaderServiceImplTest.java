package com.andersenlab.citylist.service;

import com.andersenlab.citylist.dto.CityDto;
import com.andersenlab.citylist.entity.CityEntity;
import com.andersenlab.citylist.mapper.CityMapper;
import com.andersenlab.citylist.repository.CityRepository;
import com.andersenlab.citylist.service.impl.CsvLoaderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CsvLoaderServiceImplTest {
    private final static String STUB_URL = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c9/Iss016e019375.jpg/330px-Iss016e019375.jpg";

    @Mock
    private CityRepository cityRepository;

    @Mock
    private CityMapper mockCityMapper;
    private CsvLoaderService csvLoaderService;

    @BeforeEach
    void setUp() {
        csvLoaderService = new CsvLoaderServiceImpl(cityRepository, mockCityMapper);
    }

    @Test
    void uploadCitiesFromCsvFile_whenFileIsEmpty_throwsIllegalArgumentException() {
        // Given
        final MultipartFile file = new MockMultipartFile("file", new byte[0]);

        // When
        final Throwable thrown = catchThrowable(() -> csvLoaderService.uploadCitiesFromCsvFile(file));

        // Then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Please select a correct CSV file to upload");
    }

    @Test
    void uploadCitiesFromCsvFile_whenFileIsInvalid_throwsIllegalArgumentException() {
        // Given
        final MultipartFile file = new MockMultipartFile("file", "file.txt", "text/plain", new byte[0]);

        // When
        final Throwable thrown = catchThrowable(() -> csvLoaderService.uploadCitiesFromCsvFile(file));

        // Then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void uploadCitiesFromCsvFile_withCorrectData_shouldReturnSavedCityCount() {
        // given
        List<CityDto> cityDtos = Arrays.asList(new CityDto(1L, "city1", STUB_URL),
                new CityDto(2L, "city2", STUB_URL),
                new CityDto(3L, "city3", STUB_URL));
        CityEntity cityEntity1 = new CityEntity(1L, "city1", STUB_URL);
        CityEntity cityEntity2 = new CityEntity(2L, "city2", STUB_URL);
        CityEntity cityEntity3 = new CityEntity(3L, "city3", STUB_URL);
        when(mockCityMapper.toCityEntity(cityDtos.get(0))).thenReturn(cityEntity1);
        when(mockCityMapper.toCityEntity(cityDtos.get(1))).thenReturn(cityEntity2);
        when(mockCityMapper.toCityEntity(cityDtos.get(2))).thenReturn(cityEntity3);

        MultipartFile mockFile = createMockFile(("""
                id,name,photo
                1,city1,%1$s
                2,city2,%1$s
                3,city3,%1$s""").formatted(STUB_URL));

        // when
        int savedCityCount = csvLoaderService.uploadCitiesFromCsvFile(mockFile);

        // then
        verify(cityRepository, times(3)).save(any(CityEntity.class));
        assertEquals(3, savedCityCount);
    }

    private MultipartFile createMockFile(String fileContent) {
        return new MockMultipartFile("file", "cities.csv", "text/csv", fileContent.getBytes());
    }

}