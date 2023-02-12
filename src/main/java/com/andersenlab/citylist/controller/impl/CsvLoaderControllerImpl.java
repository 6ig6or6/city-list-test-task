package com.andersenlab.citylist.controller.impl;

import com.andersenlab.citylist.controller.CsvLoaderController;
import com.andersenlab.citylist.service.CsvLoaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CsvLoaderControllerImpl implements CsvLoaderController {
    private final CsvLoaderService csvLoaderService;

    @Override
    public ResponseEntity<String> uploadCsv(MultipartFile file) {
        log.info("Starting to upload cities from csv file!");
        int uploadedCitiesCount = csvLoaderService.uploadCitiesFromCsvFile(file);
        log.info("Uploading has successfully finished!");
        return new ResponseEntity<>(uploadedCitiesCount + " cities were uploaded", HttpStatus.OK);
    }
}
