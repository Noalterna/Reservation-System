package aluczak.reservationsystem.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE_USE;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ANNOTATION_TYPE, TYPE_USE})
@Constraint(validatedBy = FirstNameValidator.class)
public @interface ValidFirstName {
    String message() default "FirstName has to have at least 3 letters and start with captial letter";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

