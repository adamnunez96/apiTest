FROM openjdk:17-jdk-alpine
RUN apk add --no-cache tzdata
VOLUME /tmp
WORKDIR /apitest
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} apitest.jar
# Configuración de variables de entorno locales
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8
ENTRYPOINT ["java", "-Duser.timezone=America/Asuncion", "-jar","/apitest/apitest.jar"]