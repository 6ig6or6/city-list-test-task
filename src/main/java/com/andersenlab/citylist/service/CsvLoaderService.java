package com.andersenlab.citylist.service;

import org.springframework.web.multipart.MultipartFile;

public interface CsvLoaderService {
    Integer uploadCitiesFromCsvFile(MultipartFile file);
}
