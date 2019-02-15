package ua.rozhkov.project.validators;

import ua.rozhkov.project.exceptions.BusinessException;

public interface ValidationService {
    void validateAge(int age) throws BusinessException;
}
