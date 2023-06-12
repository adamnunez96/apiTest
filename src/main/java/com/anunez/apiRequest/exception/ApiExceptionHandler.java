package com.anunez.apiRequest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.anunez.apiRequest.dto.ExceptionDto;


@RestControllerAdvice
public class ApiExceptionHandler extends RuntimeException {

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ExceptionDto> handleBadRequestExceptions(BadRequestException ex){
        ExceptionDto exceptionDto = ExceptionDto.builder().code(ex.getCode()).error(ex.getMessage()).build();
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InternalServerExcepcion.class)
    public ResponseEntity<ExceptionDto> handleInternalServerExceptions(InternalServerExcepcion ex){
        ExceptionDto exceptionDto = ExceptionDto.builder().code(ex.getCode()).error(ex.getMessage()).build();
        return new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ExceptionDto> handleNotFoundExceptions(NotFoundException ex){
        ExceptionDto exceptionDto = ExceptionDto.builder().code(ex.getCode()).error(ex.getMessage()).build();
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }


}