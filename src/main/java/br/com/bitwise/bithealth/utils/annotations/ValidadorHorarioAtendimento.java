package br.com.bitwise.bithealth.utils.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidadorHorarioAtendimento implements ConstraintValidator<ValidHorarioAtendimento, String> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public void initialize(ValidHorarioAtendimento constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        try {
            LocalTime time = LocalTime.parse(value, FORMATTER);
            int hour = time.getHour();
            int minute = time.getMinute();

            return hour < 24 && minute < 60;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
