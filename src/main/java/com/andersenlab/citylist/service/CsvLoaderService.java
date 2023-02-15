package com.andersenlab.citylist.service;

import org.springframework.web.multipart.MultipartFile;

public interface CsvLoaderService {

    int uploadCitiesFromCsvFile(MultipartFile file);
}
