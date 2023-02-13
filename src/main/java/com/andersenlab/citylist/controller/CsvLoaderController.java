package com.andersenlab.citylist.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Csv controller", description = "Controller for uploading cities from csv files.")
@SecurityRequirement(name = "cities")
@RequestMapping("/csv")
public interface CsvLoaderController {
    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    ResponseEntity<String> uploadCsv(@RequestParam("file") final MultipartFile file);
}
