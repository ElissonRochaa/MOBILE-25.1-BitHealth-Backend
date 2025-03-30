package br.com.bitwise.bithealth.utils.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidDataValidator.class)
public @interface ValidData {
    String message() default "A data não está no formato válido (dd/MM/yyyy)"; // Mensagem padrão
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
