package aluczak.reservationsystem.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE_USE;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ANNOTATION_TYPE, TYPE_USE})
@Constraint(validatedBy = SurnameValidator.class)
public @interface ValidSurname {
    String message() default "Surname has to start with capital letter, have at least 3 letters. Two parts of surname" +
            " can be split with single dash.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
