package bg.softuni._xmlexercise.carDealer.utils;

import jakarta.validation.ConstraintViolation;

import java.util.Set;

public interface ValidationUtil {
    public <E> boolean isValid(E entity);
    public <E> Set<ConstraintViolation<E>> getViolation(E entity);
}
