package com.anunez.apirequest.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.anunez.apirequest.dto.NewsDto;
import com.anunez.apirequest.exception.BadRequestException;
import com.anunez.apirequest.exception.InternalServerExcepcion;
import com.anunez.apirequest.exception.NotFoundException;
import com.anunez.apirequest.util.Util;


@Service
public class ApiServiceImpl implements ApiService {

    private static final Logger log = LoggerFactory.getLogger(ApiServiceImpl.class);
    
    public ResponseEntity<List<NewsDto>> getNews (String searchParameter, Boolean returnPhoto) throws Exception {
        log.info("Iniciando consulta de: {}" , searchParameter);
        List<NewsDto> responseDto = new ArrayList<>();
        try {
            if (searchParameter == null || searchParameter.isEmpty() || searchParameter.equals("") ) {
                log.error("Parametros invalidos");
                throw new BadRequestException("g268", Util.BAD_REQUEST);
            }
    
            responseDto = getDataFromWeb(searchParameter, returnPhoto);
        } catch (Exception e) {
            log.error("Error al obtener noticias: {}", e.getMessage());

            if(e instanceof BadRequestException){
                throw new BadRequestException("g268", Util.BAD_REQUEST);
            } else if(e instanceof NotFoundException){
                throw new NotFoundException("g267", Util.NOT_FOUND + searchParameter);
            } 
            throw new InternalServerExcepcion("g100", Util.INTERNAL_SERVER_ERROR);
        }
  
        return new ResponseEntity<List<NewsDto>>(responseDto, HttpStatus.OK);
    }

    public List<NewsDto> getDataFromWeb(String searchParameter, Boolean returnPhoto) {
        log.info("Obteniendo datos de la web");
        
        List<NewsDto> newsDtoList;
        String url = "https://www.hoy.com.py/search/" + searchParameter;
        Connection conn = Jsoup.connect(url).timeout(300000);
        Document doc;
        try {
            doc = conn.get();
        } catch (IOException e) {
            log.error("Error al obtener datos de la web: {}", e.getMessage());
            throw new InternalServerExcepcion("g100", Util.INTERNAL_SERVER_ERROR);
        }

        log.info("Estado de la conexion: {}" , conn.response().statusCode());

        if(conn.response().statusCode() != 200){
            log.error("Error al obtener datos de la web: {}", conn.response().statusCode());
            throw new InternalServerExcepcion("g100", Util.INTERNAL_SERVER_ERROR);
        }

        Element divContainer = doc.getElementsByClass("columns-content").first();

        if(divContainer == null){
            log.error("No se encuentran noticias para el texto: {}", searchParameter);
            throw new NotFoundException("g267", Util.NOT_FOUND + searchParameter);
        }

        Elements articleList = divContainer.select("article");

        log.info("cantidad de articulos: {} " , articleList.size());

        NewsDto newsDto;
        newsDtoList = new ArrayList<>();

        for (Element article : articleList) {
            
            newsDto = new NewsDto();
            String title = article.select("h3").text();
            String newsUrl = article.select("h3").select("a").attr("href");
            Element section = article.select("h4").first();
            String sectionName = section.select("a").text();
            String date = article.select("p").text();
            String formatedDate = Util.dateFormater(date);

            newsDto.setTitle(title);
            newsDto.setDate(date);
            newsDto.setFormatedDate(formatedDate);
            newsDto.setSection(sectionName);
            newsDto.setUrl(newsUrl);
            if(returnPhoto){
                Element photo = article.select("img").first();
                String photoUrl = photo.attr("src");
                newsDto.setPhoto(Util.imageToBase64(photoUrl));
            }else{
                newsDto.setPhoto("");
            }

            newsDtoList.add(newsDto);
        }

        return newsDtoList;
    }

}

