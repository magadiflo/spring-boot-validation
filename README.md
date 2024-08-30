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
    defer-datasource-initialization: true
  sql:
    init:
      mode: always

logging:
  level:
    org.hibernate.SQL: DEBUG

````

## Datos a poblar en la tabla

Usaremos los siguientes datos para poblar la tabla `users` cada vez que se inicie la aplicación.

````sql
TRUNCATE TABLE users RESTART IDENTITY;

INSERT INTO users(first_name, last_name, birthdate, dni, email, phone_number, age, salary, active)
VALUES ('María', 'Gómez', '1990-05-12', '12345678', 'maria.gomez@example.com', '123456789', 34, 2500.50, true),
('Juan', 'Pérez', '1985-08-20', '23456789', 'juan.perez@example.com', '234567890', 39, 3000.00, false),
('Ana', 'Martínez', '1978-03-15', '34567890', 'ana.martinez@example.com', '345678901', 46, 3200.75, true),
('Carlos', 'Fernández', '1992-07-05', '45678901', 'carlos.fernandez@example.com', '456789012', 32, 2800.00, true),
('Laura', 'López', '1995-01-25', '56789012', 'laura.lopez@example.com', '567890123', 29, 2700.00, false),
('Luis', 'González', '1988-10-30', '67890123', 'luis.gonzalez@example.com', '678901234', 35, 3100.50, true),
('Carmen', 'Rodríguez', '1975-04-10', '78901234', 'carmen.rodriguez@example.com', '789012345', 49, 2300.75, true),
('Javier', 'Hernández', '1999-09-18', '89012345', 'javier.hernandez@example.com', '890123456', 24, 2600.00, true),
('Sofía', 'Díaz', '1983-02-28', '90123456', 'sofia.diaz@example.com', '901234567', 41, 2950.00, false),
('José', 'Ruiz', '1971-11-23', '01234567', 'jose.ruiz@example.com', '012345678', 52, 4000.00, true),
('Marta', 'Moreno', '1993-06-14', '13579246', 'marta.moreno@example.com', '135792468', 31, 2550.50, true),
('David', 'Jiménez', '1984-12-01', '24681357', 'david.jimenez@example.com', '246813579', 39, 3400.00, true),
('Isabel', 'Vega', '1979-05-08', '35792468', 'isabel.vega@example.com', '357924680', 45, 3100.75, false),
('Fernando', 'Navarro', '1982-03-19', '46813579', 'fernando.navarro@example.com', '468135791', 42, 3000.00, true),
('Elena', 'Serrano', '1998-07-26', '57924680', 'elena.serrano@example.com', '579246802', 26, 2750.00, false),
('Pablo', 'Molina', '1987-11-02', '68035791', 'pablo.molina@example.com', '680357913', 36, 2900.00, true),
('Lucía', 'Ramírez', '1996-01-17', '79146802', 'lucia.ramirez@example.com', '791468024', 28, 2650.00, true),
('Ricardo', 'Ortiz', '1981-08-29', '80257913', 'ricardo.ortiz@example.com', '802579135', 43, 3100.00, false),
('Cristina', 'Torres', '1994-10-12', '91368024', 'cristina.torres@example.com', '913680246', 29, 2800.50, true),
('Miguel', 'Castro', '1976-02-09', '02479135', 'miguel.castro@example.com', '024791357', 48, 2950.00, false),
('Patricia', 'Romero', '1980-06-23', '13580246', 'patricia.romero@example.com', '135802468', 44, 3100.00, true),
('Raúl', 'Santos', '1989-03-02', '24691357', 'raul.santos@example.com', '246913579', 35, 3500.00, true),
('Natalia', 'Ortega', '1973-09-15', '35702468', 'natalia.ortega@example.com', '357024680', 51, 2750.00, false),
('Hugo', 'Iglesias', '1991-04-08', '46813568', 'hugo.iglesias@example.com', '468135681', 33, 3300.00, true),
('Sara', 'Silva', '1997-12-21', '57924679', 'sara.silva@example.com', '579246791', 26, 2500.00, true),
('Andrés', 'Crespo', '1986-07-11', '68035780', 'andres.crespo@example.com', '680357801', 38, 3200.00, true),
('Eva', 'Ferrer', '1985-11-19', '79146891', 'eva.ferrer@example.com', '791468911', 38, 2800.00, false),
('Santiago', 'Rubio', '1982-01-30', '80257902', 'santiago.rubio@example.com', '802579021', 42, 3050.00, true),
('Rosa', 'Medina', '1995-05-06', '91368013', 'rosa.medina@example.com', '913680132', 29, 2950.00, false),
('Adrián', 'Vicente', '1990-03-24', '02479124', 'adrian.vicente@example.com', '024791243', 34, 3100.00, true);
````

## Entidad

A continuación se muestra la entidad que usaremos para mapear la tabla `users` de la base de datos. Recordar que las
validaciones las haremos sobre clases `DTO` y no directamente sobre las entidades.

````java

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String dni;
    private String email;
    private String phoneNumber;
    private Integer age;
    private Double salary;
    private Boolean active;
}
````

## Repositorio

````java
public interface UserRepository extends JpaRepository<User, Long> {
}
````

## DTOs

A continuación se muestran los dtos que usaremos tanto para recibir información del cliente como para enviar información
hacia el cliente. En el dto `UserRequest` será donde estableceremos las distintas anotaciones de validación, pero eso lo
haremos más adelante.

````java

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserRequest {
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String dni;
    private String email;
    private String phoneNumber;
    private Integer age;
    private Double salary;
    private Boolean active;
}
````

El siguiente dto nos servirá para enviar los datos hacia el cliente.

````java

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String dni;
    private String email;
    private String phoneNumber;
    private Integer age;
    private Double salary;
    private Boolean active;
}
````

## User Mapper

````java

@Component
public class UserMapper {
    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthdate(user.getBirthdate())
                .dni(user.getDni())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .age(user.getAge())
                .salary(user.getSalary())
                .active(user.getActive())
                .build();
    }

    public User toUser(UserRequest userRequest) {
        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .birthdate(userRequest.getBirthdate())
                .dni(userRequest.getDni())
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .age(userRequest.getAge())
                .salary(userRequest.getSalary())
                .active(userRequest.getActive())
                .build();
    }
}
````

## Servicio

En el servicio es donde usaremos los dtos que creamos inicialmente. Esto lo hacemos para evitar interactuar directamente
con las entidades, por ejemplo, las validaciones lo haremos en los dtos y no en las entidades, pero aun ese tema lo
veremos más adelante.

````java
public interface UserService {
    List<UserResponse> findAllUsers();

    UserResponse findUserById(Long userId);

    UserResponse saveUser(UserRequest userRequest);

    UserResponse updateUser(Long userId, UserRequest userRequest);

    void deleteUserById(Long userId);
}
````

````java

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponse> findAllUsers() {
        return this.userRepository.findAll().stream()
                .map(this.userMapper::toUserResponse)
                .toList();
    }

    @Override
    public UserResponse findUserById(Long userId) {
        return this.userRepository.findById(userId)
                .map(this.userMapper::toUserResponse)
                .orElseThrow(() -> new UserNotFoundException("No existe el usuario buscado con id " + userId));
    }

    @Override
    @Transactional
    public UserResponse saveUser(UserRequest userRequest) {
        User userDB = this.userRepository.save(this.userMapper.toUser(userRequest));
        return this.userMapper.toUserResponse(userDB);
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long userId, UserRequest userRequest) {
        return this.userRepository.findById(userId)
                .map(userDB -> {
                    User user = this.userMapper.toUser(userRequest);
                    user.setId(userId);
                    return user;
                })
                .map(this.userRepository::save)
                .map(this.userMapper::toUserResponse)
                .orElseThrow(() -> new UserNotFoundException("No existe el usuario para actualizar con id " + userId));
    }

    @Override
    @Transactional
    public void deleteUserById(Long userId) {
        User userDB = this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No existe el usuario para eliminar con id " + userId));
        this.userRepository.deleteById(userId);
    }
}
````

## Manejo de excepciones

Si revisamos la clase anterior, vemos que estamos lanzando una excepción personalizada `UserNotFoundException`. Esta
excepción la manejaremos con una clase especial que a continuación se muestra.

````java

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(UserNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(Map.of("message", exception.getMessage())));
    }

}
````

````java
public record ErrorResponse(Map<String, String> errors) {
}
````

## Controlador

````java

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAllUsers() {
        return ResponseEntity.ok(this.userService.findAllUsers());
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserResponse> findUser(@PathVariable Long userId) {
        return ResponseEntity.ok(this.userService.findUserById(userId));
    }

    @PostMapping
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = this.userService.saveUser(userRequest);
        return ResponseEntity
                .created(URI.create("/api/v1/users/" + userResponse.getId()))
                .body(userResponse);
    }

    @PutMapping(path = "/{userId}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest, @PathVariable Long userId) {
        return ResponseEntity.ok(this.userService.updateUser(userId, userRequest));
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        this.userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

}
````

## Usando curl

Si ahora mismo ejecutamos la aplicación (teniendo obviamente el contenedor de postgres levantado) y hacemos las
siguientes peticiones, veremos que todo está funcionando correctamente. En mi caso ya no colocaré los resultados, sino
más bien los comandos de curl de las distintas peticiones para tenerlos disponibles.

````bash
$ curl -v http://localhost:8080/api/v1/users | jq
$ curl -v http://localhost:8080/api/v1/users/30 | jq
$ curl -v -X POST -H "Content-Type: application/json" -d "{\"firstName\": \"Liz\", \"lastName\": \"Gonzales\", \"birthdate\": \"1989-08-31\", \"dni\": \"45718525\", \"email\": \"libra_08_31@gmail.com\", \"phoneNumber\": \"985252525\", \"age\": 35, \"salary\": 3200, \"active\": true}" http://localhost:8080/api/v1/users | jq
$ curl -v -X PUT -H "Content-Type: application/json" -d "{\"firstName\": \"Lizbeth\", \"lastName\": \"Carbajal\", \"birthdate\": \"1989-08-31\", \"dni\": \"45718525\", \"email\": \"libra_08_31@gmail.com\", \"phoneNumber\": \"943858596\", \"age\": 34, \"salary\": 2500, \"active\": true}" http://localhost:8080/api/v1/users/31 | jq
$ curl -v -X DELETE http://localhost:8080/api/v1/users/31 | jq
````

## Prácticas recomendadas de API REST

- Siempre validar los datos (documento JSON) en la payload de la solicitud.
- Si los datos no son válidos, devuelva un `status code 400 (Bad Request)` con un mensaje de error claro.

## [Jakarta Bean Validation](https://beanvalidation.org/)

- `Java Bean Validation` es una especificación de Java que le permite expresar restricciones en modelos de objetos a
  través de anotaciones como `@NotNull`, `@NotBlank`, `@Length`, etc.
- Proporciona API para validar objetos y gráficos de objetos.
- Puede escribir restricciones personalizadas si las integradas no satisfacen sus necesidades.
- `Java Bean Validation` ahora es `Jakarta Bean Validation`.

### [Lista de restricciones Java Bean Validation](https://jakarta.ee/specifications/bean-validation/3.1/apidocs/jakarta/validation/constraints/package-summary.html)

Contiene todas las restricciones proporcionadas por `Jakarta Validation` también se denominan restricciones
integradas.

Estas restricciones no cubren todos los casos de uso funcional, pero representan todos los bloques fundamentales para
expresar restricciones de bajo nivel en los básicos de JDK.

A continuación se muestran algunos constraints que nos proporciona el `Jakarta Bean Validation`:

- Email
- Max
- Min
- NotBlank
- NotEmpty
- NotNull
- FutureOrPresent
- Null
- Size

## [Hibernate Validator: La implementación de referencia de Validación de Bean](https://docs.jboss.org/hibernate/stable/validator/api/org/hibernate/validator/constraints/package-summary.html)

- `Hibernate Validator` es una implementación de referencia de la especificación `Java Bean Validation`.
- Proporciona restricciones y API integradas adicionales.
- `Hibernate validator` no viene con `Spring Data JPA` de manera predeterminada. Debemos declarar la dependencia
  `spring-boot-starter-validation`.

A continuación se muestran algunas restricciones que nos proporciona el `Hibernate Validator`:

- CreditCardNumber
- Currency
- EAN
- ISBN
- Length
- Normalized
- Range
- UniqueElements
- URL

