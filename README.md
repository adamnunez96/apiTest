# Test

Proyecto test de API Rest con Java, Spring Boot y Maven

## Requisitos

Para construir y ejecutar la aplicación necesita:

- [java jdk 17](https://www.oracle.com/java/technologies/downloads/#java17)
- [Maven 3.8.6](https://maven.apache.org)

Estas dos herramientas tambien pueden ser instaladas localmente solo para su usuario mediante la herramienta sdkman

- [sdkman](https://sdkman.io/)

- [Docker](https://www.docker.com/)

## Ejecutando la aplicación localmente

Hay varias formas de ejecutar una aplicación Spring Boot en su máquina local. Una forma es ejecutar el método `main` en la clase `ApiRequestApplication.java` desde su IDE.

Alternativamente, puede usar el [complemento Spring Boot Maven](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) así:

```shell
mvn compile spring-boot:run
```

Esta api tambien puede ser ejecutada a travez de un contenedor docker, para ello seguir los pasos:

- Primeramente se debe generar el archivo .jar

```shell
mvn clean install
```
- Una vez generado el .jar debemos crear la imagen

```shell
docker build -t apirequest:1.0.0-SNAPSHOT .
```
- Por ultimo, levantar el contenedor

```shell
docker run -d -e LANG=es_PY.UTF-8 --name apirequest -p 8080:8080 apirequest:1.0.0-SNAPSHOT .
```

## Documentacion

Para ver documentacion de la API mediante Swagger UI se debe levantar el proyecto e ingresar al siguiente URL: http://localhost:8080/swagger-ui.html#/

 - Método: GET
 - Request URL: http://localhost:8080/api/consulta?search=enciso&photo=true
    - search: texto de busqueda (opcional).
    - photo: parámetro que indica si debe retornar la imágen de la publicacón codificada en Base64 (opcional)

## Respuesta de ejemplo

```
[
   {
        "title": "Cerro Porteño busca su primer triunfo en la altura de La Paz",
        "date": "6 de junio de 2023",
        "formatedDate": "2023-06-06 00:00:00",
        "section": "Deportes",
        "url": "https://www.hoy.com.py/deportes/cerro-porteno-busca-su-primer-triunfo-en-la-altura-de-la-paz",
        "photo": ""
    },
    {
        "title": "El gol de Enciso al City es el mejor de la temporada",
        "date": "4 de junio de 2023",
        "formatedDate": "2023-06-04 00:00:00",
        "section": "Deportes",
        "url": "https://www.hoy.com.py/deportes/el-gol-de-enciso-al-city-es-el-mejor-de-la-temporada",
        "photo": ""
    }
]
```
