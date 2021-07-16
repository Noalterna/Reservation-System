package aluczak.reservationsystem.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class FirstNameValidator implements ConstraintValidator<ValidFirstName, String> {
    private String regex = "^[A-ZĆŁŚŹŻ][a-ząćęłńóśźż]{2,}$";

    @Override
    public void initialize(ValidFirstName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String firstName, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pat = Pattern.compile(regex);
        return pat.matcher(firstName).matches();
    }
}
