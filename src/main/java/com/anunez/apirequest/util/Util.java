package com.anunez.apirequest.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

        String inputFormat = "d 'de' MMMM 'de' yyyy";
        String outputFormat = "yyyy-MM-dd HH:mm:ss";

        try{

            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputFormat);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(outputFormat);

            LocalDate localDate = LocalDate.parse(date, inputFormatter);
            log.info("localDate {}", localDate);
            return localDate.atStartOfDay().format(outputFormatter);

        }catch(Exception e){
            log.error("Error al formatear fecha: {}", e.getMessage());
            throw new InternalServerExcepcion("g100", INTERNAL_SERVER_ERROR);
        }   

    }

}
