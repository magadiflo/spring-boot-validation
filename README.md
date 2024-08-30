# [Spring Boot REST API Request Validation](https://www.youtube.com/watch?v=eX8Wr5MeEnw)

Se toma como referencia el tutorial de `Code Java (youtube)` para la realización de este ejemplo. Adicionalmente, se
incluirán otros ejemplos que los referenciaré a continuación.

---

## Aplicación Base

A continuación se mostrará rápidamente la construcción del proyecto base sobre la cual más adelante nos dedicaremos a
aplicar las validaciones correspondientes.

## Dependencias

La dependencia central para este proyecto es el `spring-boot-starter-validation`. Esta dependencia nos provee un
conjunto de anotaciones para validar campos de manera sencilla.

````xml
<!--Spring Boot 3.3.3-->
<!--Java 21-->
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
````

## Docker compose para crear contenedor de PostgreSQL

Creamos el archivo `compose.yml` donde agregaremos el siguiente servicio para crear un contendor de postgres con docker.

````yml
services:
  postgres:
    image: postgres:15.2-alpine
    container_name: c-postgres
    restart: unless-stopped
    environment:
      POSTGRES_DB: db_spring_boot_validation
      POSTGRES_USER: magadiflo
      POSTGRES_PASSWORD: magadiflo
    ports:
      - '5433:5432'
    volumes:
      - postgres_data:/var/lib/postgresql/data

volumes:
  postgres_data:
    name: postgres_data
````

## Crea configuración de la aplicación

En el archivo `application.yml` agregamos la siguiente configuración.

````yml
server:
  port: 8080
  error:
    include-message: always

spring:
  application:
    name: spring-boot-validation
  datasource:
    url: jdbc:postgresql://localhost:5433/db_spring_boot_validation
    username: magadiflo
    password: magadiflo
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        format_sql: true

logging:
  level:
    org.hibernate.SQL: DEBUG
````
