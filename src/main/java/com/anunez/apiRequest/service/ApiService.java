package com.anunez.apiRequest.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.anunez.apiRequest.dto.NewsDto;

public interface ApiService {
    public ResponseEntity<List<NewsDto>>  getNews(String searchValue, Boolean returnPhoto) throws Exception;
}
