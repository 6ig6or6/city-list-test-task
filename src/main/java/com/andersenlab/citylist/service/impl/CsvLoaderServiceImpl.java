package com.andersenlab.citylist.service.impl;

import com.andersenlab.citylist.dto.CityDto;
import com.andersenlab.citylist.exception.CSVParsingException;
import com.andersenlab.citylist.mapper.CityMapper;
import com.andersenlab.citylist.repository.CityRepository;
import com.andersenlab.citylist.service.CsvLoaderService;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class CsvLoaderServiceImpl implements CsvLoaderService {

    private static final char DELIMITER = ',';

    private static final String CSV_FILE_EXTENSION = ".csv";

    private final CityRepository cityRepository;

    private final CityMapper cityMapper;

    @Override
    public int uploadCitiesFromCsvFile(MultipartFile file) {
        if (file.isEmpty() || !Objects.requireNonNull(file.getOriginalFilename()).endsWith(CSV_FILE_EXTENSION)) {
            throw new IllegalArgumentException("Please select a correct CSV file to upload");
        }
        final CsvMapper csvMapper = new CsvMapper();
        csvMapper.registerModule(new JavaTimeModule());
        csvMapper.enable(CsvParser.Feature.SKIP_EMPTY_LINES);
        final CsvSchema csvSchema = getCsvSchema(csvMapper);
        final Validator validator = getValidator();
        int savedCityCount = 0;
        try (MappingIterator<CityDto> iterator = csvMapper
                .readerWithSchemaFor(CityDto.class)
                .with(csvSchema)
                .readValues(new InputStreamReader(file.getInputStream(), Charset.forName("windows-1251")))) {
            while (iterator.hasNext()) {
                final CityDto cityDto = iterator.nextValue();
                Set<ConstraintViolation<CityDto>> violations = validator.validate(cityDto);
                if (violations.isEmpty()) {
                    cityRepository.save(cityMapper.toCityEntity(cityDto));
                    savedCityCount++;
                }
            }
        } catch (final IOException e) {
            log.error("Csv uploading failed after uploading {} cities", savedCityCount);
            throw new CSVParsingException();
        }
        return savedCityCount;
    }

    private CsvSchema getCsvSchema(final CsvMapper csvMapper) {
        return csvMapper
                .typedSchemaFor(CityDto.class)
                .withHeader()
                .withStrictHeaders(true)
                .withEscapeChar('"')
                .withNullValue("null")
                .withColumnSeparator(DELIMITER);
    }

    private Validator getValidator() {
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            return validatorFactory.getValidator();
        }
    }
}
