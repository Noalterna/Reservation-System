package aluczak.reservationsystem.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE_USE;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ANNOTATION_TYPE, TYPE_USE})
@Constraint(validatedBy = TicketTypeValidator.class)
public @interface ValidTicketType {
    String message() default "Ticket type must be one of: adult, child, student.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}