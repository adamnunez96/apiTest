package com.anunez.apirequest.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anunez.apirequest.exception.InternalServerExcepcion;


public class Util {

    private Util() {}

    private static final Logger log = LoggerFactory.getLogger(Util.class);

    
    public static final String INTERNAL_SERVER_ERROR = "Error interno del servidor";
    public static final String BAD_REQUEST = "Parametros invalidos";
    public static final String NOT_FOUND = "No se encuentran noticias para el texto: ";

    public static String imageToBase64(String imageUrl) {
        log.info("Convirtiendo imagen a base64");
        byte[] imageBytes = null;
        try {
            
            imageBytes = getPhotoFromCloudServer(imageUrl);

        } catch (Exception e) {
            log.error("Error al convertir imagen a base64: {}", e.getMessage());
            throw new InternalServerExcepcion("g100", INTERNAL_SERVER_ERROR);
        }
        return java.util.Base64.getEncoder().encodeToString(imageBytes);
    }

    public static byte[] getPhotoFromCloudServer(String url) {
        log.info("Obteniendo imagen desde el servidor");

        try {
            
            URL urlPhoto = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlPhoto.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(300000);

            InputStream inputStream = connection.getInputStream();

            ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
            byte[] buffer = new byte[10240];  
            int len = 0;  
            while ((len = inputStream.read(buffer)) != -1) {  
                outStream.write(buffer, 0, len);  
            }  
            inputStream.close();  
            return outStream.toByteArray(); 

        } catch (Exception e) {
            log.error("Error al obtener imagen desde el servidor: {}", e.getMessage());
            throw new InternalServerExcepcion("g100", INTERNAL_SERVER_ERROR);
        }
    }

    public static String dateFormater(String date) {

        try{

            String formattedDate = date.replace(" de ", "-");
            String[] dateSplit = formattedDate.split("-");

            String finalDate = getFinalDate(dateSplit);

            return finalDate;

        }catch(Exception e){
            log.error("Error al formatear fecha: {}", e.getMessage());
            throw new InternalServerExcepcion("g100", INTERNAL_SERVER_ERROR);
        }   
    }

    public static String getFinalDate(String[] date) throws Exception{

        String day = date[0];
        String month = date[1].toLowerCase();
        String year = date[2];

        if(day.length() == 1){
            day = "0" + day;
        }

        switch(month){
            case "enero":
                month = "01";
                break;
            case "febrero":
                month = "02";
                break;
            case "marzo":
                month = "03";
                break;
            case "abril":
                month = "04";
                break;
            case "mayo":
                month = "05";
                break;
            case "junio":
                month = "06";
                break;
            case "julio":
                month = "07";
                break;
            case "agosto":
                month = "08";
                break;
            case "setiembre":
                month = "09";
                break;
            case "octubre":
                month = "10";
                break;
            case "noviembre":
                month = "11";
                break;
            case "diciembre":
                month = "12";
                break;
            default:
                month = "";
                break;
        }

        return year + "-" + month + "-" + day + " 00:00:00";
    }

}
