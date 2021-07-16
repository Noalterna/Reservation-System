package aluczak.reservationsystem.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class SurnameValidator implements ConstraintValidator<ValidSurname, String> {
    private String regex = "^[A-ZĆŁŚŹŻ][a-ząćęłńóśźż]{2,}$";
    private String regexWithDash = "^[A-ZĆŁŃŚŻŹ][a-ząćęłńóśżź]{2,}-[A-ZĆŁŃŚŻŹ][a-ząćęłńóśżź]{2,}$";

    @Override
    public void initialize(ValidSurname constraintAnnotation) {
    }

    @Override
    public boolean isValid(String surname, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pat = Pattern.compile(regex);
        Pattern pat2 = Pattern.compile(regexWithDash);
        return pat.matcher(surname).matches() || pat2.matcher(surname).matches();
    }
}
