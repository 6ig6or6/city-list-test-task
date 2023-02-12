package com.andersenlab.citylist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/csv")
public interface CsvLoaderController {
    @PostMapping("/upload")
    ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file);
}
