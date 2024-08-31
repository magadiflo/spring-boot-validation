package dev.magadiflo.app.model.dto;

import lombok.*;

import java.time.LocalDate;

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
    private String notificationPreference;
}
