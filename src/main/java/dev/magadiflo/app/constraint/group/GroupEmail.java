package dev.magadiflo.app.constraint.group;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@ReportAsSingleViolation
@NotBlank(message = "Email must not be blank") //Este mensaje será ignorado
@Email(message = "Email is not valid") //Este mensaje será ignorado
public @interface GroupEmail {

    //Este será el mensaje a retornar si falla @Email o @Length
    String message() default "Error en el campo email, verifique que no esté en blanco y que sea un email con formato válido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
