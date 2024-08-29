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

