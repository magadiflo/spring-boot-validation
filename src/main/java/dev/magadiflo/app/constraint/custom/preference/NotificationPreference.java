package dev.magadiflo.app.constraint.custom.preference;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {NotificationPreferenceValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface NotificationPreference {
    String message() default "La preferencia de notificación debe ser email o mobilePhone";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
