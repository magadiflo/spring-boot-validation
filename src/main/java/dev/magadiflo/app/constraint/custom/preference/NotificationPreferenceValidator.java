package dev.magadiflo.app.constraint.custom.preference;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class NotificationPreferenceValidator implements ConstraintValidator<NotificationPreference, String> {

    private final List<String> notificationPreferences = List.of("email", "mobilePhone");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null) {
            return this.notificationPreferences.contains(value);
        }
        return true;
    }
}
