package com.anunez.apirequest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anunez.apirequest.dto.NewsDto;
import com.anunez.apirequest.service.ApiServiceImpl;


@RestController
public class ApiControllerImpl implements ApiController {

    ApiServiceImpl apiService = new ApiServiceImpl();
    
    @GetMapping("api/consulta")
    public ResponseEntity<List<NewsDto>> getNews(@RequestParam(value = "search", required = false) String searchParameter,
                                @RequestParam(value = "photo", required = false) Boolean returnPhoto) throws Exception {
        
            
        return apiService.getNews(searchParameter, returnPhoto);
    
    }
}
