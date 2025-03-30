package br.com.bitwise.bithealth.utils.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidDataValidator implements ConstraintValidator<ValidData, String> {

    private static final String DATE_PATTERN = "dd/MM/yyyy";

    @Override
    public void initialize(ValidData constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        dateFormat.setLenient(false);

        try {
            Date date = dateFormat.parse(value);

            Date today = new Date();
            if (date.after(today)) {
                return false;
            }

            return dateFormat.format(date).equals(value);
        } catch (Exception e) {
            return false;
        }
    }
}
