package bg.softuni._gamestore.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component

public class ValidatorImpl implements Validator{
    private final jakarta.validation.Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    @Override
    public <E> boolean isValid(E entity) {
        return this.validator.validate(entity).isEmpty();
    }

    @Override
    public <E> Set<ConstraintViolation<E>> validate(E entity) {
        return this.validator.validate(entity);
    }
}
