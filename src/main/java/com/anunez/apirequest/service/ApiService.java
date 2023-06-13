package com.anunez.apirequest.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.anunez.apirequest.dto.NewsDto;

public interface ApiService {
    public ResponseEntity<List<NewsDto>>  getNews(String searchValue, Boolean returnPhoto) throws Exception;
}
