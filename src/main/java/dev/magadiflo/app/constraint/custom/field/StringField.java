package dev.magadiflo.app.constraint.custom.field;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {StringFieldValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface StringField {
    String message() default "El campo contiene un valor incorrecto";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /* Métodos agregados */

    String messageNotNull() default "No está permitido que el campo sea nulo";

    String messageNotEmpty() default "No está permitido que el campo esté vacío";

    String messageLength() default "La longitud del campo es incorrecta";

    boolean notEmpty() default false;

    int min() default 0;

    int max() default Integer.MAX_VALUE;
}