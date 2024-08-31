package dev.magadiflo.app.model.dto;

import dev.magadiflo.app.constraint.custom.field.StringField;
import dev.magadiflo.app.constraint.custom.preference.NotificationPreference;
import dev.magadiflo.app.constraint.group.GroupEmail;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserRequest {
    @NotBlank(message = "El nombre no puede estar en blanco")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String firstName;

    @StringField(notEmpty = true, min = 2, max = 50,
            messageNotNull = "El campo no puede ser nulo",
            messageNotEmpty = "El campo no puede ser vacío",
            messageLength = "El campo debe tener un tamaño de entre 2 y 50 caracteres")
    private String lastName;

    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private LocalDate birthdate;

    @NotBlank(message = "El DNI no puede estar en blanco")
    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener exactamente 8 dígitos")
    private String dni;

    @GroupEmail
    private String email;

    @Pattern(regexp = "\\+?[0-9. ()-]{7,25}", message = "Número de teléfono inválido")
    private String phoneNumber;

    @Min(value = 0, message = "La edad mínima es 0")
    @Max(value = 120, message = "La edad máxima es 120")
    private Integer age;

    @DecimalMin(value = "0.0", inclusive = false, message = "El salario debe ser mayor que 0")
    @Digits(integer = 10, fraction = 2, message = "El salario debe tener un máximo de 10 dígitos enteros y 2 decimales")
    private Double salary;

    @NotNull(message = "El estado activo no puede ser nulo")
    private Boolean active;

    @NotBlank(message = "La preferencia de notificación no puede estar en blanco")
    @NotificationPreference
    private String notificationPreference;
}
