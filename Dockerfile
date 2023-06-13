FROM openjdk:17-jdk-alpine
VOLUME /tmp
WORKDIR /apitest
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} apitest.jar
# Configuración de variables de entorno locales
ENV LANG C.UTF-8
ENV LC_ALL C.UTF-8
ENTRYPOINT ["java", "-Duser.timezone=America/Asuncion","-jar","/apitest/apitest.jar"]