package aluczak.reservationsystem.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TicketTypeValidator implements ConstraintValidator<ValidTicketType, String> {
    @Override
    public void initialize(ValidTicketType constraintAnnotation) {
    }

    @Override
    public boolean isValid(String ticketType, ConstraintValidatorContext constraintValidatorContext) {
        return ticketType.equals("adult")
                || ticketType.equals("child")
                || ticketType.equals("student");
    }
}