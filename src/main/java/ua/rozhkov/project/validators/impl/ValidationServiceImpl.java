package ua.rozhkov.project.validators.impl;

import ua.rozhkov.project.exceptions.BusinessException;
import ua.rozhkov.project.validators.ValidationService;

public class ValidationServiceImpl implements ValidationService {
    @Override
    public void validateAge(int age) throws BusinessException {
        if ((age < 0) || (age > 120)) throw new BusinessException("Incorrect age!!!");
    }
}
