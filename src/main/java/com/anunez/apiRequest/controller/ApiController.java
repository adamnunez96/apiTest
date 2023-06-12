package com.anunez.apiRequest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import com.anunez.apiRequest.dto.NewsDto;

public interface ApiController {
    public ResponseEntity<List<NewsDto>> getNews(@RequestParam(value = "search", required = false) String searchParameter,
                                @RequestParam(value = "photo", required = false) Boolean returnPhoto) throws Exception;
}
