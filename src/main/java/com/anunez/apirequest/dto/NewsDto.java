package com.anunez.apirequest.dto;

import lombok.Data;

@Data
public class NewsDto {
    private String title;
    private String date;
    private String formatedDate;
    private String section;
    private String url;
    private String photo;
}
