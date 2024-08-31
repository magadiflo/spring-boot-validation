package dev.magadiflo.app.constraint.custom.field;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StringFieldValidator implements ConstraintValidator<StringField, String> {

    private Boolean notEmpty;
    private Integer min;
    private Integer max;
    private String messageNotNull;
    private String messageNotEmpty;
    private String messageLength;

    @Override
    public void initialize(StringField constraintAnnotation) {
        this.notEmpty = constraintAnnotation.notEmpty();
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
        this.messageNotNull = constraintAnnotation.messageNotNull();
        this.messageNotEmpty = constraintAnnotation.messageNotEmpty();
        this.messageLength = constraintAnnotation.messageLength();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        if (this.notEmpty && value == null) {
            context.buildConstraintViolationWithTemplate(this.messageNotNull).addConstraintViolation();
            return false;
        }

        if (this.notEmpty && value.trim().isEmpty()) {
            context.buildConstraintViolationWithTemplate(this.messageNotEmpty).addConstraintViolation();
            return false;
        }

        if ((this.min > 0 || this.max < Integer.MAX_VALUE) &&
            (value != null) &&
            (value.length() < this.min || value.length() > this.max)) {
            context.buildConstraintViolationWithTemplate(this.messageLength).addConstraintViolation();
            return false;
        }

        return true;
    }
}
