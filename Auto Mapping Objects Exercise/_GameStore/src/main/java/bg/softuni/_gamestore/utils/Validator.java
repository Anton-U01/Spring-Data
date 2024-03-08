package bg.softuni._gamestore.utils;

import jakarta.validation.ConstraintViolation;

import java.util.Set;

public interface Validator {
    <E> boolean isValid(E entity);
    <E> Set<ConstraintViolation<E>> validate(E entity);
}
