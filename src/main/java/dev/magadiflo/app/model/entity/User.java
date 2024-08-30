package dev.magadiflo.app.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
