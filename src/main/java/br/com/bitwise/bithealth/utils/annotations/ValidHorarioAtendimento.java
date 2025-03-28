package br.com.bitwise.bithealth.utils.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidadorHorarioAtendimento.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidHorarioAtendimento {
    String message() default "Horário final de atendimento inválido. Formato esperado: HH:mm";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
