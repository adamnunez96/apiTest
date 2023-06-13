package com.anunez.apirequest.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionDto {
    private String code;
    private String error;
}
