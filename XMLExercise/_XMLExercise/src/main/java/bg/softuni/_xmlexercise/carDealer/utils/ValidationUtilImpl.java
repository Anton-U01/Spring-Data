package bg.softuni._xmlexercise.carDealer.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component

public class ValidationUtilImpl implements ValidationUtil{
    private final Validator validator;

    public ValidationUtilImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public <E> boolean isValid(E entity) {
        return this.getViolation(entity).isEmpty();
    }

    @Override
    public <E> Set<ConstraintViolation<E>> getViolation(E entity) {
        return this.validator.validate(entity);
    }
}
